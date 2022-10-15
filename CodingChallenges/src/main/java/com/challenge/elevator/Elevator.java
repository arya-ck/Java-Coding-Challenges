package com.challenge.elevator;

import java.util.*;

public class Elevator {

    private static final int minFloor = 0;
    private static final int maxFloor = 10;
    private static int processingTime = 500;
    private static int lastStop = minFloor;

    private int currentFloor;
    private Direction currentDirection;

    private Map<Integer, List<Integer>> requestedPathsMap;
    private Boolean[] currentFloorDestinations;

    public Elevator() {
        this.currentFloor = 0;
        this.currentDirection = Direction.UP;
        this.requestedPathsMap = new HashMap<>();
        this.currentFloorDestinations = new Boolean[maxFloor + 1];

        Arrays.fill(this.currentFloorDestinations, Boolean.FALSE);
    }

    public void setProcessingTime(int processingTime) {
        Elevator.processingTime = processingTime;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public Map<Integer, List<Integer>> getRequestedPathsMap() {
        return this.requestedPathsMap;
    }

    public Boolean[] getCurrentFloorDestinations() {
        return this.currentFloorDestinations;
    }

    public void start() throws InterruptedException {
        do {
            System.out.println("--------");
            processFloor(currentFloor);
            System.out.println("--------");
        } while(currentDirection != Direction.IDLE);

        System.out.println("No one is waiting and " +
                "no one is looking to go anywhere");
        System.out.println("Chilling for now");
    }

    public void lunchtimeElevatorRush() {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            callElevator(random.nextInt(11),
                    random.nextInt(10) + 1);
        }
    }

    private boolean isFloorInvalid(int start, int destination){
        if(isInvalidFloor(start) || isInvalidFloor(destination) || start == destination){
            System.out.println("INVALID FLOORS. Try again");
            return true;
        }
        return false;
    }

    private void updateFloorDirections(int start, int dest){

        // set direction
        if(currentDirection == Direction.IDLE){
            if(start < dest){
                currentDirection = Direction.UP;
            } else {
                currentDirection = Direction.DOWN;
            }
        }

        // update current destinations
        if(currentDirection == Direction.UP){
            setCurrentDirFloorsUp();
        } else if(currentDirection == Direction.DOWN){
            setCurrentDirFloorDown();
        }
    }
    private void setCurrentDirFloorsUp(){
        // update current destinations when elevator is going up
        requestedPathsMap.entrySet().stream().forEach(path -> {
            if (path.getValue() != null) {
                path.getValue().stream().forEach(d -> {
                    if (d > path.getKey()) {
                        this.currentFloorDestinations[d] = true;
                    }
                });
            }
        });
    }

    public void setCurrentDirFloorDown() {
        // update current destinations when elevator is going down
        requestedPathsMap.entrySet().stream().forEach(path -> {
            if (path.getValue() != null) {
                path.getValue().stream().forEach(d -> {
                    if (d < path.getKey()) {
                        this.currentFloorDestinations[d] = true;
                    }
                });
            }
        });
    }

    private void addStopAtDest(int start, int destination){
        List<Integer> currentStops = requestedPathsMap.get(start);

        if(currentStops != null){ // add to list of destinations
            currentStops.add(destination);
            this.requestedPathsMap.put(start, currentStops);
        } else {// create a new list and add destination
            List<Integer> stops = new ArrayList<>();
            stops.add(destination);
            this.requestedPathsMap.put(start, stops);
        }

    }

    private void updateLastStop(){
        if(currentDirection == Direction.UP) {  // update last stop when direction is upward
            Optional<Integer> lastDest = this.requestedPathsMap.values().stream()
                    .map(v -> {
                        Optional<Integer> max = v.stream()
                                .filter(v1 -> v1 <= maxFloor)   // find destinations within max floor
                                .max((v2, v3) -> v2 > v3 ? v2 : v3); // find last stop for each start position
                        return max.isPresent() ? max.get() : (minFloor);
                    })
                    .reduce((v1, v2) -> v1 > v2 ? v1 : v2); // find overall last stop
            if (lastDest.isPresent() && lastDest.get()>lastStop) {
                this.lastStop = lastDest.get();
            }
        } else if(currentDirection == Direction.DOWN) {  // update last stop when direction is downward
            Optional<Integer> lastDest = this.requestedPathsMap.values().stream()
                    .map(v -> {
                        Optional<Integer> max = v.stream()
                                .filter(v1 -> v1 >= minFloor )  // find destinations greater than min floor
                                .reduce((v2, v3) -> v2<v3? v2: v3); // find last stop for each start position
                        return max.isPresent()? max.get(): maxFloor;
                    })
                    .reduce((v1, v2) -> v1<v2? v1: v2); // find overall last stop
            if(lastDest.isPresent() && lastDest.get()<lastStop){
                this.lastStop = lastDest.get();
            }
        }
    }

    public void callElevator(int start, int destination) {

        // validate floor
        if(isFloorInvalid(start, destination)){
            return;
        }

        // set elevator start position
        if(currentDirection == Direction.IDLE){
            currentFloor = start;
        }

        // add path from start to destination
        addStopAtDest(start, destination);

        // update the floor direction
        updateFloorDirections(start, destination);

        // update last stop, considering the destination value
        updateLastStop();
    }

    private void removeDestinationFromPath(int floor){
        // remove from destinations
        currentFloorDestinations[floor] = false;

        // update the paths & remove paths with floor as destination
        requestedPathsMap.entrySet().stream().forEach((path) -> {
            if(path.getValue().contains(floor)){
                if(currentDirection == Direction.UP && path.getKey()<=floor) {
                    path.getValue().remove(path.getValue().indexOf(floor));
                } else if(currentDirection ==Direction.DOWN && path.getKey()>=floor){
                    path.getValue().remove(path.getValue().indexOf(floor));
                }
            }

            requestedPathsMap.put(path.getKey(), path.getValue());
        });
    }

    private boolean isBoardingStop(int floor){
        // check if path from floor exists & will be in the current direction
        return requestedPathsMap.containsKey(floor) && requestedPathsMap.get(floor) !=null
                && requestedPathsMap.get(floor).size() != 0
                && requestedPathsMap.get(floor).stream().anyMatch(d -> currentFloorDestinations[d]);
    }

    private boolean shouldMoveDown(){
        // check if unprocessed paths exists in downward direction
        return requestedPathsMap.entrySet().stream()
                .anyMatch(path -> path.getValue() != null
                        && path.getValue().stream().anyMatch(d -> currentDirection == Direction.UP? d < path.getKey(): d > path.getKey())
                );
    }

    private void processFloor(int floor) throws InterruptedException {

        // return if elevator is idle
        if(currentDirection == Direction.IDLE){
            return;
        }

        // if current floor is a destination, unboard
        if(currentFloorDestinations[floor]) {
            removeDestinationFromPath(floor);
            System.out.println("UNBOARDING at Floor "+floor);
        }

        // check if there are paths to be processed when elevator goes down
        boolean shouldGoDown = shouldMoveDown();

        // set direction to down when max floor is reached & there are downward paths left
        if(floor == maxFloor && shouldGoDown){
            currentDirection = Direction.DOWN;
            setCurrentDirFloorDown();
            updateLastStop();
        }

        // board if required
        if(isBoardingStop(floor)){
            System.out.println("BOARDING at Floor "+floor);
        }

        // stop elevator in any of the conditions
        // if going down and min floor reached
        // if going up and max floor reached, and there are no paths unprocessed
        // if last stop reached, and there are no paths unprocessed
        if((currentDirection == Direction.DOWN && floor == minFloor)
                || (currentDirection == Direction.UP && floor == maxFloor && !shouldGoDown)
                || (floor == lastStop && !shouldGoDown)){
            removeDestinationFromPath(floor);
            currentDirection = Direction.IDLE;
            return;
        }

        // move elevator
        moveElevator();
    }

    private void moveElevator() throws InterruptedException {

        if(currentDirection == Direction.UP){   // move up
            moveUp();
        } else if(currentDirection == Direction.DOWN) { // move down
            moveDown();
        }
    }

    private void moveUp() throws InterruptedException {
        currentFloor++;
        System.out.println("GOING UP TO " + currentFloor);
        Thread.sleep(processingTime);
    }

    private void moveDown() throws InterruptedException {
        currentFloor--;
        System.out.println("GOING DOWN TO " + currentFloor);
        Thread.sleep(processingTime);
    }

    private boolean isInvalidFloor(int floor) {
        // floor outside range
        return floor < minFloor || floor > maxFloor;
    }
}
