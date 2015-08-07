package demo.classification;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class wekaTest2 {
	public static void main(String args[]) throws Exception
	{
//		Instances data=DataSource.read("H:/Program Files/Weka-3-7/data/bank.arff");		
//		data.setClassIndex(data.numAttributes()-1);
		
		Instances data=DataSource.read("D:/Program Files/Weka-3-7/data/contact-lenses.arff");
		data.setClassIndex(0);
		
		Evaluation eval = new Evaluation(data);			//evaluation
		
		String[] options = new String[1];
		
	    options[0] = "-S 0 -K 2 -D 3 -G 0.0 -R 0.0 -N 0.5 -M 40.0 -C 1.0 -E 0.0010 -P 0.1 -B";    //    	   
	    LibSVM tree = new weka.classifiers.functions.LibSVM();
	    tree.setOptions(options);    // set the options		
		
//	    J48 tree = new J48();    // new instance of tree
		
//		Classifier tree = new weka.classifiers.trees.Id3();

	    tree.buildClassifier(data);    // build classifier
	   
	   eval.crossValidateModel(tree, data, 10, new Random(1));
	     //eval.evaluateModel(tree, data);
	    System.out.println(eval.toSummaryString("\nResults\n\n", true));
	    System.out.println(eval.toClassDetailsString());
	    System.out.println(eval.toMatrixString());
 
	    
//	    for (int i = 0; i < data.numInstances(); i++) {  
//			//double clsLabel = cls.classifyInstance(test.instance(i));  
//			double clsLabel[]=tree.distributionForInstance(data.instance(i));
//			for(double d: clsLabel)
//			System.out.print(d+" ");  
//			System.out.println();
//		}

	}
}

