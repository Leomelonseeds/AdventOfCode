package com.leomelonseeds.aoc.y2022.day17;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day17_2 {

    public static void main(String[] args) throws NumberFormatException {
        char[] jets = Utils.getInput().get(0).toCharArray();
        // SOLUTION: Utils.println(581395347L * 2704 + 2890 + (4980 - 2891));
        Set<List<Integer>> felled = new HashSet<>();
        Set<List<Integer>> rockJetCombos = new HashSet<>();
        // Initialize felled with floor
        for (int i = -4; i <= 4; i++) {
            felled.add(Arrays.asList(new Integer[] {i, 0}));
        }
        int rockNum = 0;
        int jetNum = 0;
        int prevDiff = 0;
        for (long i = 1; i <= 1000000000000L; i++) {
            rockNum = rockNum > 4 ? 0 : rockNum;
            
            // Detect rock-jet combos that already exist to find repetition
            // After finding repetition, find first rock/jet num to repeat (0, 600)
            // And find all other locations where this combo repeats. This turns out to
            // be every 1720 iterations, with the highest num increasing by 2704
            // each time this combo repeats.
            if (!rockJetCombos.add(Arrays.asList(new Integer[] {rockNum, jetNum}))) {
                Utils.println("Repetition found at " + i + " with " + rockNum + ", " + jetNum);
            }
            
            int spawny = getHighestPos(felled) + 4;
            Set<List<Integer>> rock = new HashSet<>();
            switch (rockNum) {
            case 0:
                rock.add(Arrays.asList(new Integer[] {-1, spawny}));
                rock.add(Arrays.asList(new Integer[] {0, spawny}));
                rock.add(Arrays.asList(new Integer[] {1, spawny}));
                rock.add(Arrays.asList(new Integer[] {2, spawny}));
                break;
            case 1:
                rock.add(Arrays.asList(new Integer[] {-1, spawny + 1}));
                rock.add(Arrays.asList(new Integer[] {0, spawny + 2}));
                rock.add(Arrays.asList(new Integer[] {0, spawny + 1}));
                rock.add(Arrays.asList(new Integer[] {0, spawny}));
                rock.add(Arrays.asList(new Integer[] {1, spawny + 1}));
                break;
            case 2:
                rock.add(Arrays.asList(new Integer[] {-1, spawny}));
                rock.add(Arrays.asList(new Integer[] {0, spawny}));
                rock.add(Arrays.asList(new Integer[] {1, spawny}));
                rock.add(Arrays.asList(new Integer[] {1, spawny + 1}));
                rock.add(Arrays.asList(new Integer[] {1, spawny + 2}));
                break;
            case 3:
                rock.add(Arrays.asList(new Integer[] {-1, spawny}));
                rock.add(Arrays.asList(new Integer[] {-1, spawny + 1}));
                rock.add(Arrays.asList(new Integer[] {-1, spawny + 2}));
                rock.add(Arrays.asList(new Integer[] {-1, spawny + 3}));
                break;
            case 4:
                rock.add(Arrays.asList(new Integer[] {-1, spawny}));
                rock.add(Arrays.asList(new Integer[] {-1, spawny + 1}));
                rock.add(Arrays.asList(new Integer[] {0, spawny + 1}));
                rock.add(Arrays.asList(new Integer[] {0, spawny}));
            }
            
            boolean atRest = false;
            int step = 0;
            while (!atRest) {
                // Test if should use jet or not
                Set<List<Integer>> nextPos = new HashSet<>();
                boolean downwards = false;
                if (step % 2 == 0) {
                    jetNum = jetNum >= jets.length ? 0 : jetNum;
                    char jet = jets[jetNum];
                    int dir = jet == '<' ? -1 : 1; // -1 is left, 1 is right
                    for (List<Integer> list : rock) {
                        int cur = list.get(0); // x pos
                        nextPos.add(Arrays.asList(new Integer[] {cur + dir, list.get(1)}));
                    }
                    jetNum++;
                } else {
                    downwards = true;
                    for (List<Integer> list : rock) {
                        int cur = list.get(1); // y pos
                        nextPos.add(Arrays.asList(new Integer[] {list.get(0), cur - 1}));
                    } 
                }
                
                // Test if this touches an edge or the ground (or any other rock)
                boolean success = true;
                for (List<Integer> pos : nextPos) {
                    if (!felled.contains(pos) && Math.abs(pos.get(0)) < 4) {
                        continue;
                    }
                    
                    if (downwards) {
                        atRest = true;
                    }
                    
                    success = false;
                    break;
                }
                
                if (success) {
                    rock = new HashSet<>(nextPos);
                }
                
                step++;
            }
            
            felled.addAll(rock);
            rockNum++;
            
            // trim list every 1000 iterations
            if (i % 1000 == 0) {
                int toTrim = getHighestPos(felled) - 500;
                for (List<Integer> pos : new HashSet<>(felled)) {
                    if (pos.get(1) <= toTrim) {
                        felled.remove(pos);
                    }
                }
            }

            // SOLUTION FOUND USING MATH TO SUM UP REPEATING PATTERNS
            if ((i - 3556) % 1720 == 0) {
                int highest = getHighestPos(felled);
                Utils.println(highest - prevDiff + ", " + highest + ", " + i);
                prevDiff = highest;
            }
        }
        
        Utils.println(getHighestPos(felled));
    }
    
    private static int getHighestPos(Set<List<Integer>> positions) {
        int max = 0;
        for (List<Integer> pos : positions) {
            int y = pos.get(1);
            if (y > max) {
                max = y;
            }
        }
        return max;
    }
}
