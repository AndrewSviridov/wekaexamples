package demo.classification;

import java.io.BufferedWriter;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class LogisticRegression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		process();
	}
	
	
	public static void process() {
		
		String dir1="queston/NTimesCross";
		handlerFolder(dir1);

		String dir2="answer/NTimesCross" ;
		handlerFolder(dir2);
		
		String dir3="questionanswer/NTimesCross" ;
		handlerFolder(dir3);
	}
	
	
	public static void handlerFolder(String strPath) {		
		String allResultFileName = strPath+"/allResult.txt";
		
		for(int i=1; i<=5; i++){
			String folderName = strPath+"/"+String.valueOf(i);
			runLogistic(folderName, allResultFileName);
		}	
	}
	
	public static void runLogistic(String folderName, String allResultFileName) {
		
		try{
			BufferedWriter allbw=new BufferedWriter(new FileWriter(allResultFileName,true));
			
			
			String trainFileName=folderName+"/train.arff";	
			String testFileName=folderName+"/test.arff";	
			String resultFileName=folderName+"/result.txt";
			
				
			BufferedWriter bw=new BufferedWriter(new FileWriter(resultFileName));
			
			Instances train = DataSource.read(trainFileName);    // from somewhere
			Instances test = DataSource.read(testFileName); 

			train.setClassIndex(0);
			test.setClassIndex(0);
		
			
            
			 //占内�? 非常大，
 		    String[] options =weka.core.Utils.splitOptions("-D -R 1.0E-8 -M -1"); 
 		   Logistic classifier =new Logistic();
 		    classifier.setOptions(options);
		    
			
			classifier.buildClassifier(train);
			
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(classifier, test);
			
			//输出评价指标的�??
			System.out.println(eval.toSummaryString("\nResults\n\n", true));
//			System.out.println(eval.toSummaryString());
			allbw.write(eval.toSummaryString("\nResults\n\n", true));
			allbw.flush();
			
			System.out.println(eval.toClassDetailsString());
//			System.out.println(eval.toMatrixString());
			allbw.write(eval.toClassDetailsString());
			allbw.flush();
			
			
			//输出预测结果的概率分�?
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
			allbw.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	
}
