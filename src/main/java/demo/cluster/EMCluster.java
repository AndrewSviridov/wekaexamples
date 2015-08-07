package demo.cluster;


import java.io.File;

import weka.clusterers.EM;
import weka.core.DistanceFunction;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
 

public class EMCluster {
 	
	public static void main(String[] args) {		      
		EM KM = new EM();			
		runCluster(KM);				
	}      
    
	public static void runCluster(EM KM){	
	   Instances ins = null;            
      
       try{                    
           //读入样本
           File file= new File("D:\\Program Files\\Weka-3-7\\data\\bank.arff");
           ArffLoader loader = new ArffLoader();
           loader.setFile(file);
           ins = loader.getDataSet();                    
           
           KM.setNumClusters(4); //设置聚类要得到的类别数量                   
         
           KM.buildClusterer(ins); //聚类                                  
	        
           System.out.println("result:"+KM.toString()); //聚类结果           
	                  
	       System.out.println("\n输出每个实例属于每个聚类的概�?");   
	       for (int i = 0; i < ins.numInstances(); i++) {          
	    	   int clusterId = KM.clusterInstance(ins.instance(i)); 
	    	   System.out.print(clusterId+"	");	
	    	   
	    	   double[] labelProbability = KM.distributionForInstance(ins.instance(i)); 	           
	    	   for(double p : labelProbability){
	        	   System.out.print(String.valueOf(p)+" ");										
			   } 
	           System.out.println();
	       }
       }catch(Exception e){
           e.printStackTrace();
       }
    }
	
	
	
	
}

