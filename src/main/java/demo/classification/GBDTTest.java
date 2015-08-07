package demo.classification;

import weka.classifiers.Evaluation;
import weka.classifiers.meta.AdditiveRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class GBDTTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runOrderRegression("F:/order-predict/userDriverImpact/successfulOrderTrain.arff",
				"F:/order-predict/userDriverImpact/successfulOrderTest.arff");
		
	}
	
	public static Double runOrderRegression(String trainFile, String testFile) {		
		Double predictOrder = 0.0;
		Double trueOrder = 0.0;
		try{						
			String trainWekaFileName=trainFile;	
			String testWekaFileName=testFile;	
			
			Instances train = DataSource.read(trainWekaFileName);
			Instances test = DataSource.read(testWekaFileName); 

			train.setClassIndex(0);
			test.setClassIndex(0);					
			
			AdditiveRegression classifier = new AdditiveRegression();
			//LinearRegression classifier = new LinearRegression();
			
			//String[] options =weka.core.Utils.splitOptions("-S 1 -R 1.0E-8"); 
			//classifier.setOptions(options);
					
		    classifier.buildClassifier(train);			
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(classifier, test);			
			
			predictOrder = classifier.distributionForInstance(test.instance(0))[0];	
			trueOrder = test.instance(0).classValue();
			
			System.out.println(trueOrder+"	"+predictOrder);
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return predictOrder;
	}	

}
