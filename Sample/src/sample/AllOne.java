import java.util.*;

class Node {
    Set<String> keys;
    int count;
    Node next;
    Node prev;
    public Node(int cnt){
        this.count=cnt;
        keys = new HashSet<>();
    }

}

public class AllOne {
    Map<String,Integer> keyToCountMap;
    Map<Integer,Node> countToNodeMap;
    Node head,tail;

    public AllOne(){
        head=new Node(-1);
        tail=new Node(-1);
        head.next=tail;
        tail.prev=head;
        keyToCountMap=new HashMap<>();
        countToNodeMap=new HashMap<>();

    }

    public void inc(String key){
        if(keyToCountMap.containsKey(key)){
            int existingCount = keyToCountMap.get(key);
            Node existingNode = countToNodeMap.get(existingCount);
            if(existingNode.next!=tail && existingNode.next.count==existingCount+1){
                existingNode.next.keys.add(key);

            } else {
                Node newNode = new Node(existingCount+1);
                newNode.keys.add(key);
                addNextToNode(existingNode,newNode);
                countToNodeMap.put(existingCount+1, newNode);

            }
            existingNode.keys.remove(key);
            if(existingNode.keys.isEmpty()){
                deleteNode(existingNode);
                countToNodeMap.remove(existingCount);
            }
            keyToCountMap.put(key, existingCount+1);

        } else {
            Node one = countToNodeMap.get(1);
            if (one != null) {
                one.keys.add(key);
            } else {
                Node newNode = new Node(1);
                newNode.keys.add(key);
                addNextToNode(head, newNode);
                countToNodeMap.put(1, newNode);
            }
            keyToCountMap.put(key, 1);
        }
        

    }

    public void dec(String key){
        if(keyToCountMap.containsKey(key)){
            int existingCount = keyToCountMap.get(key);
            Node existingNode = countToNodeMap.get(existingCount);
            if(existingNode.prev!=head && existingNode.prev.count==existingCount-1 && existingCount!=1){
                existingNode.prev.keys.add(key);

            } else if(existingCount!=1) {
                Node newNode = new Node(existingCount-1);
                newNode.keys.add(key);
                addPrevToNode(existingNode,newNode);
                countToNodeMap.put(existingCount-1, newNode);

            }
            existingNode.keys.remove(key);
            if(existingNode.keys.isEmpty()){
                deleteNode(existingNode);
                countToNodeMap.remove(existingCount);
            }
            if(existingCount!=1){
                keyToCountMap.put(key, existingCount-1);
            } else {
                keyToCountMap.remove(key);
            }

        } 

    }

    public String getMaxKey(){
        if(head.next==tail){
            return "";
        }
        Set<String> set = tail.prev.keys;
        return set.iterator().next();

    }

    public String getMinKey(){
        if(head.next==tail){
            return "";
        }
        Set<String> set = head.next.keys;
        return set.iterator().next();
    }


    public void addNextToNode(Node sourceNode,Node destNode){
        destNode.next=sourceNode.next;
        destNode.prev=sourceNode;
        sourceNode.next.prev=destNode;
        sourceNode.next=destNode;


    }

    public void addPrevToNode(Node sourceNode,Node destnode){
        destnode.prev=sourceNode.prev;
        destnode.next=sourceNode;
        sourceNode.prev.next=destnode;
        sourceNode.prev=destnode;

    }

    public void deleteNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
        node.next=node.prev=null;

    }

}
