package demo.cluster;
		
import java.io.File;

import weka.clusterers.Clusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
		  
		public class WekaCluster { 
		      
		    private Instances instances=null;    
		    
		    private Clusterer clusterer=null; 
		      
		    public WekaCluster(Clusterer clusterer) 
		    { 
		        this.clusterer=clusterer; 
		    } 

 /*		    
 			private StringToWordVector filter=new StringToWordVector();   
		    static String path="E:\\datasets\\alt.atheism\\"; 
		   
		    public void loadInstances() throws Exception 
		    { 
		        String name="text"; 
		        FastVector attributes=new FastVector(1); 
		        attributes.addElement(new Attribute("message",(FastVector)null)); 
		        instances=new Instances(name,attributes,100); 
		        for(File file : new File(path).listFiles()) 
		        { 
		            String message=getAllMessage(file); 
		            Instance instance=new Instance(1); 
		            Attribute attribute=instances.attribute("message"); 
		            instance.setValue(attribute, attribute.addStringValue(message)); 
		            instance.setDataset(instances); 
		            instances.add(instance); 
		        } 
		          
		        filter.setInputFormat(instances);        
		        Instances filtedData=Filter.useFilter(instances,filter);         
		        instances=filtedData;
		    }
		    
		    private String getAllMessage(File file) { 
		        StringBuilder sb=new StringBuilder(); 
		        try
		        { 
		            BufferedReader br=new BufferedReader(new FileReader(file)); 
		            String line; 
		            while(true) 
		            { 
		                if((line=br.readLine())==null) break; 
		                sb.append(line.trim()); 
		            }        
		            br.close(); 
		        } catch (Exception e){} 
		        return sb.toString(); 
		    } 
*/		        
		    	
		    
		    public void loadInstances() throws Exception 
		    { 	        
		    	File file= new File("D:\\Program Files\\Weka-3-7\\data\\bank.arff");
		        ArffLoader loader = new ArffLoader();
		        loader.setFile(file);
		        instances = loader.getDataSet();		        
		    } 
		      
		    public void testCluster() throws Exception 
		    { 
		        clusterer.buildClusterer(instances); 
		        		     	        
		        
		        for (int i = 0; i < instances.numInstances(); i++) {          
		            int cluster = clusterer.clusterInstance(instances.instance(i)); 
		            System.out.println("\t"+(i+1)+":"+cluster);  
		        }        
		        System.out.println(clusterer.numberOfClusters()); 
		        System.out.println(clusterer.toString()); 
		    } 
		      
		    
		  
		    public static void main(String[] args) throws Exception { 
		        SimpleKMeans cluster=new SimpleKMeans();//构�?�聚类算�? 
		        cluster.setNumClusters(6); 
		          
		        WekaCluster sk=new WekaCluster(cluster); 
		        
		        sk.loadInstances(); 
		        
		        sk.testCluster();//测试聚类效果 
		    } 
		} 
