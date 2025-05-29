
import java.util.*;



public class VotingApplication {

    public List<String> getVotingResult(List<List<String>> ballots){
        List<String> result = new ArrayList<>();
        Map<String,Integer> scoreMap = new HashMap<>();
        TreeMap<Integer,LinkedHashSet<String>> scoreSortedMap = new TreeMap<>(Collections.reverseOrder());
        Map<String,Integer[]> indexScoreMap = new HashMap<>();
        for(List<String> ballot: ballots){
            for(int i=0;i<ballot.size();i++){
                String candidate=ballot.get(i);
                Integer existingScore = scoreMap.getOrDefault(candidate,0);
                if(scoreMap.containsKey(candidate)){
                    scoreSortedMap.get(existingScore).remove(candidate);
                    if(scoreSortedMap.get(existingScore).isEmpty()){
                        scoreSortedMap.remove(existingScore);
                    }
                }
                
                Integer newScore = existingScore+(3-i)
                scoreMap.put(candidate,newScore);
                
                scoreSortedMap.putIfAbset(newScore,new LinkedHashSet<>());
                indexScoreMap.putIfAbset(candidate,new Integer[3]);
                indexScoreMap.get(candidate)[i]++;
                scoreSortedMap.get(newScore).add(candidate);
        }
        }

        /*for(Map.Entry<Integer,LinkedHashSet<String>> entry: scoreSortedMap.entrySet()){
            result.addAll(entry.getValue());
        }*/

        result.addAll(scoreMap.keySet());
        result.sort(new Comparator<String>(){
            public int compare(String c1,String c2){
                Integer c1Score = scoreMap.get(c1);
                Integer c2Score = scoreMap.get(c2);

                /* 
                if(c2Score==c1Score){
                    c1Score = indexScoreMap.get(c1)[0];
                    c2Score = indexScoreMap.get(c2)[0];
                }
                if(c2Score==c1Score){
                    c1Score = indexScoreMap.get(c1)[1];
                    c2Score = indexScoreMap.get(c2)[1];
                }
                if(c2Score==c1Score){
                    c1Score = indexScoreMap.get(c1)[2];
                    c2Score = indexScoreMap.get(c2)[2];
                }
                */
                return c2Score-c1Score;
            }
        });
        return result;
    }

}
