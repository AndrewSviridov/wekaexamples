package demo.cluster;

import java.io.File;

import weka.clusterers.SimpleKMeans;
import weka.core.DistanceFunction;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
 

public class WekaCluster1 {
 

    /**
     * @param args
     */   
		
	
	
	
	public static void main(String[] args) {
		SimpleKMeans  KM = new SimpleKMeans();      
		//EM KM = new EM();		
		//XMeans  KM = new XMeans();  //KM.setMinNumClusters(6);  Cannot handle binary attributes!
	
		runCluster(KM);				
	}
       
    
	public static void runCluster(SimpleKMeans KM){	
	   Instances ins = null;
       
	   Instances tempIns = null;
       
       DistanceFunction disFun = null;
       try{          
           
           //读入样本
           File file= new File("D:\\Program Files\\Weka-3-7\\data\\bank.arff");
           ArffLoader loader = new ArffLoader();
           loader.setFile(file);
           ins = loader.getDataSet();
           
           
           System.out.println( ins.classIndex());
           System.out.println( ins.numAttributes());
           
           
           

           //设置聚类算法内部的距离计算方�?,默认的采用了欧几里得距离
           //KM.setDistanceFunction(disFun);
            
           //设置聚类要得到的类别数量
           KM.setNumClusters(6);                      
          
           //使用聚类算法对样本进行聚�?
           KM.buildClusterer(ins);          
           
           //可自己指定初始中心对样本进行聚类
           //KM.buildClusterer_daping(insCentre);//buildClusterer_daping为自己定义的函数        
            
           tempIns = KM.getClusterCentroids();//聚类中心
           System.out.println("CentroIds: \n" + tempIns);
	        
           System.out.println("result: \n"+KM.toString()); //聚类结果           
	                  
	       System.out.println("\n输出每个实例属于每个聚类的概�?");   
	       for (int i = 0; i < ins.numInstances(); i++) {          
	    	   int clusterId = KM.clusterInstance(ins.instance(i)); 
	    	   System.out.print(clusterId+"	");	
	    	   
	    	   double[] labelProbability = KM.distributionForInstance(ins.instance(i)); 	           
	    	   for(double p: labelProbability){
	        	   System.out.print(String.valueOf(p)+" ");										
			   } 
	           System.out.println();
	       }
       }catch(Exception e){
           e.printStackTrace();
       }
    }
	
	
	
	
}

