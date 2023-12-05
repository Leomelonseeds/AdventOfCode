package com.leomelonseeds.aoc.y2023.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leomelonseeds.aoc.Utils;

public class Day4_2 {

    public static void main(String[] args) {
        
        // this solution is slow as fuck lmaoo
        // better solution to improve for next time:
        // instead of iterating over all cards, iterate over each card once
        // and simply multiply the amount of next cards by the amount of 
        // cards you currently have for this specific card
        Map<Integer, Integer> cardNums = new HashMap<>();
        cardNums.put(1, 1);
        

        List<String> input = Utils.getInput();
        for (int i = 0; i < input.size(); i++) {
            cardNums.put(i, 1);
        }
        
        int curCard = 0;
        int sum = 1;
        while (curCard < cardNums.size()) {
            String s = input.get(curCard);
            String[] sargs = s.split("\\|");
            String s1 = sargs[0].split(":")[1];
            String s2 = sargs[1];
            
            Pattern pattern = Pattern.compile("\\d+");
            
            Matcher matcher1 = pattern.matcher(s1);
            List<Integer> card = new ArrayList<>();
            while(matcher1.find()) {
                card.add(Integer.parseInt(matcher1.group(0)));
            }

            List<Integer> have = new ArrayList<>();
            Matcher matcher2 = pattern.matcher(s2);
            while(matcher2.find()) {
                have.add(Integer.parseInt(matcher2.group(0)));
            }
            
            int pt = 0;
            for (int i : have) {
                if (card.contains(i)) {
                    pt++;
                    int nextCardIndex = curCard + pt;
                    if (cardNums.containsKey(nextCardIndex)) {
                        cardNums.put(nextCardIndex, cardNums.get(nextCardIndex) + 1);
                    }
                }
            }
            
            if (cardNums.get(curCard) > 1) {
                cardNums.put(curCard, cardNums.get(curCard) - 1);
            } else {
                Integer next = cardNums.get(++curCard);
                sum += next == null ? 0 : next;
            }
        }
        
        Utils.println(sum);
    }

}
