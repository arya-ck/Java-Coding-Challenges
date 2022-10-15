package com.challenge.changeinvestment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * You want to start investing, so you decide for every purchase, you'll invest the change.
 * In this challenge, your job is to figure out the average amount invested per purchase.
 * This month, you decided to invest the leftover change from each purchase into the stock market. For example, when you purchase your $5.20 coffee, you put 73 cents into your investment account.
 * Given a list of each purchase made, your challenge is to create a function that returns the average amount you invested in the stock market.
 */
public class InvestingChange {

    public static double calculateAvgChangeInvested(List<Double> purchases) {
        // find the change for each purchase
        List<Double> purchaseSavings = purchases.stream()
                .map((purchase) -> (Math.ceil(purchase) - purchase))
                .collect(Collectors.toList());
        Optional<Double> sum = purchaseSavings.stream().reduce( (e, red) -> e+red);

        // total number of purchases
        int count = purchaseSavings.size();

        // average savings calculation
        return sum.isPresent()? (sum.get()/count): 0;
    }

    public static void main(String[] args) {
        List<Double> purchases = List.of(12.38, 38.29, 5.27, 3.21);
        System.out.println(calculateAvgChangeInvested(purchases));
    }
}
