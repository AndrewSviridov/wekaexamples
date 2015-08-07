package demo.classification;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestPercentageClassification {

	

	public static void main(String[] args) {		
		runLinearRegression("F:/EDJData/11-12");
	}
	


	public static void runLinearRegression(String folderName) {
		
		try{						
			String FileName=folderName+"/t_order_1_month4_Test_weka.arff";		
			String resultFileName=folderName+"/result.txt";				
			BufferedWriter bw=new BufferedWriter(new FileWriter(resultFileName));
			
			Instances inst = DataSource.read(FileName);    // from somewhere
			int trainSize = (int) Math.round(inst.numInstances() * 66 / 100);//percent	               
	        int testSize = inst.numInstances() - trainSize;
	        
	        int randomSeed = 2;
	        Random random = new Random(randomSeed);
            inst.randomize(random);
	        
	        Instances train = new Instances(inst, 0, trainSize);
	        Instances test = new Instances(inst, trainSize, testSize);
			
			train.setClassIndex(0);
			test.setClassIndex(0);
			
			String[] options =weka.core.Utils.splitOptions("-D -S 1 -R 1.0E-8"); 
			LinearRegression classifier = new LinearRegression();
 		    classifier.setOptions(options);
			
		    classifier.buildClassifier(train);
			
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(classifier, test);			
	
			
			double[] coef = classifier.coefficients();
            System.out.println(Arrays.toString(coef));
            System.out.println(classifier.toString());
           
            
			//输出评价指标的值
			System.out.println(eval.toSummaryString("\nResults\n\n", true));
			
//			System.out.println(eval.toClassDetailsString());
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
