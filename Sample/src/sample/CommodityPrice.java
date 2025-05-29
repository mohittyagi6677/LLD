import java.util.*;
public class CommodPrice {
    class ValueWithVersion{
        int value;
        int version;
        public ValueWithVersion(int val,int ver){
            this.val=val;
            this.version=ver;
        }
    }
    Map<Integer,Integer> timeStampToPrice;
    TreeMap<Integer,Integer> priceToCountMap;
    int maxTimeStamp=0;
    int version=0;
    Map<Integer,List<ValueWithVersion>> timeStampToVersionMap = new HashMap<>();



    public void updatePrice(int timeStamp,int price){
        Integer existingPrice = timeStampToPrice.get(timeStamp);
        if(existingPrice!=null){
            priceToCountMap.put(existingPrice,priceToCountMap.get(priceToCountMap)-1);
            if(priceToCountMap.get(existingPrice)==0){
                priceToCountMap.remove(existingPrice);
            }
        }
        timeStampToPrice.put(timeStamp,price);
        priceToCountMap.putIfAbsent(price,0);
        priceToCountMap.put(price,priceToCountMap.get(price)+1);
        maxTimeStamp=Math.max(maxTimeStamp,timeStamp);
        timeStampToVersionMap.putIfAbsent(timeStamp,new ArrayList<>());
        timeStampToVersionMap.get(timeStamp).add(new ValueWithVersion(price,version));
        int checkpoint=version;
        version++;
        return checkpoint;

    }

    public int getCurrentPrice(){
        return timeStampToPrice.get(maxTimeStamp);
    }

    public int getMaxPrice(){
        return priceToCountMap.lastKey();
    }

    public int getMinPrice(){
        return priceToCountMap.firstKey();
    }

    public int getPrice(int timeStamp,int version){
        List<ValueWithVersion> valuesWithVersion = timeStampToVersionMap.get(timeStamp);
        // binary search in the last with floor value of version

    }

}
