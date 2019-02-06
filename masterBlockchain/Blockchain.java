package masterBlockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    public static void main(String[] args) {
        List<Block> blockChainList =  new ArrayList<>();
        System.out.println("Adding Genesis Block");
        Block genesis = new Block("BlockChain", 0);
        blockChainList.add(genesis);
        System.out.println(("Adding Block1"));
        Block helloBlock = new Block("Hello", blockChainList.get(blockChainList.size()-1).getHash());
        blockChainList.add(helloBlock);
        System.out.println(("Adding Block2"));
        Block worldBlock = new Block("World", blockChainList.get(blockChainList.size()-1).getHash());
        blockChainList.add(worldBlock);
        System.out.println(("Adding Block3"));
        Block dZoneBlock = new Block("DZone", blockChainList.get(blockChainList.size()-1).getHash());
        blockChainList.add(dZoneBlock);
       
        System.out.println("----------------------");
        System.out.println("- BlockChain Content -");
        System.out.println("----------------------");
        blockChainList.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("Is this Blockchain valid?: " + validate(blockChainList));
        System.out.println("---------------------------");
        
        
        // corrupt block chain by modifying one of the block
        Block hiBlock = new Block("Hi", genesis.getHash());
        int index = blockChainList.indexOf(helloBlock);
        blockChainList.remove(index);
        blockChainList.add(index, hiBlock);
        System.out.println("Simulated a Corrupted block chain by replacing 'Hello' block with 'Hi' Block");
        System.out.println("---------------------");
        System.out.println("- BlockChain -");
        System.out.println("---------------------");
        blockChainList.forEach(System.out::println);
        System.out.println("---------------------");
        System.out.println("Is valid?: " + validate(blockChainList));
        System.out.println("---------------------");
    }
    
    
    private static boolean validate(List<Block> blockChain) {
        boolean result = true;
        Block lastBlock = null;
        for(int i = blockChain.size() -1; i >= 0; i--) {
            if(lastBlock == null) {
                lastBlock = blockChain.get(i);
            }
            else {
                Block current = blockChain.get(i);
                if(lastBlock.getPreviousHash() != current.getHash()) {
                    result = false;
                    break;
                }
                lastBlock = current;
            }
        }
        return result;
    }
}