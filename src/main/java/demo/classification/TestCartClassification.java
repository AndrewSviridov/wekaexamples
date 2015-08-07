package demo.classification;

import java.io.BufferedWriter;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.SimpleCart;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestCartClassification {

	

	public static void main(String[] args) {
			// TODO Auto-generated method stub
		//runLinearRegression("F:/EDJData");
		
		runJ48("D:/Program Files (x86)/Weka-3-6/data");
	}
	



	public static void runJ48(String folderName) {
		
		try{
			
			
			String trainFileName=folderName+"/iris.arff";	
			String testFileName=folderName+"/iris.arff";	
			String resultFileName=folderName+"/result.txt";
			
				
			BufferedWriter bw=new BufferedWriter(new FileWriter(resultFileName));
			
			Instances train = DataSource.read(trainFileName);    // from somewhere
			Instances test = DataSource.read(testFileName); 

			train.setClassIndex(train.numAttributes()-1);
			test.setClassIndex(train.numAttributes()-1);
		
			
			
 		    SimpleCart classifier = new SimpleCart();
			String[] options =weka.core.Utils.splitOptions("-D -S 1 -M 2.0 -N 5 -C 1.0"); 
			classifier.setOptions(options);
			
		    classifier.buildClassifier(train);
			
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(classifier, test);
			
			//输出评价指标的值
			System.out.println(eval.toSummaryString("\nResults\n\n", true));
			System.out.println(classifier.toString());

//			System.out.println(eval.toMatrixString());				
			
			//输出预测结果的概率分布
			for (int k = 0; k < test.numInstances(); k++) {  
				double clsLabel[]=classifier.distributionForInstance(test.instance(k));

				for(double d: clsLabel){
					//System.out.print(d+" ");
					bw.write(String.valueOf(d)+" ");										
				}
				//System.out.println();
				bw.write("\r\n");
				bw.flush();
			} 
			
			bw.close();
	    
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	
}
