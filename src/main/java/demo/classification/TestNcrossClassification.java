package demo.classification;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestNcrossClassification {

	

	public static void main(String[] args) {		
		runLinearRegression("F:/EDJData/11-12");
	}
	


	public static void runLinearRegression(String folderName) {
		
		try{						
			String FileName=folderName+"/t_order_1_month4_Test_weka.arff";		
			String resultFileName=folderName+"/result.txt";				
			BufferedWriter bw=new BufferedWriter(new FileWriter(resultFileName));			
			Instances inst = DataSource.read(FileName);    // from somewhere
            			
			inst.setClassIndex(0);						
			
			String[] options =weka.core.Utils.splitOptions("-D -S 1 -R 1.0E-8"); 
			LinearRegression classifier = new LinearRegression();
 		    classifier.setOptions(options);
			
		    classifier.buildClassifier(inst);
			
			Evaluation eval = new Evaluation(inst);			
			
			for(int i=0;i<10;i++){
				eval=new Evaluation(inst);
				eval.crossValidateModel(classifier, inst, 10, new Random(i));//实现交叉验证模型
			}
			
			double[] coef = classifier.coefficients();
            System.out.println(Arrays.toString(coef));
            System.out.println(classifier.toString());
           
            
			//输出评价指标的值
			System.out.println(eval.toSummaryString("\nResults\n\n", true));
			
			//输出预测结果的概率分布
			for (int k = 0; k < inst.numInstances(); k++) {  
				double clsLabel[]=classifier.distributionForInstance(inst.instance(k));

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
