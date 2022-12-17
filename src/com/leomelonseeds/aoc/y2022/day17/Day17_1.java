package com.leomelonseeds.aoc.y2022.day17;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day17_1 {

    public static void main(String[] args) throws NumberFormatException {
        char[] jets = Utils.getInput().get(0).toCharArray();
        Set<List<Integer>> felled = new HashSet<>();
        // Initialize felled with floor
        for (int i = -4; i <= 4; i++) {
            felled.add(Arrays.asList(new Integer[] {i, 0}));
        }
        int rockNum = 0;
        int jetNum = 0;
        for (int i = 0; i < 2022; i++) {
            Utils.println(i);
            rockNum = rockNum > 4 ? 0 : rockNum;
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
