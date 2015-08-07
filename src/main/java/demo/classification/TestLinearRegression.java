package demo.classification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;

public class TestLinearRegression {

	public static void main(String[] args) {
		
		TestOrder();
	}
	
	public static void TestOrder(){
		String inputFile = "F:/EDJData/t_order_1_month4_Train_weka.arff";
        java.io.Reader r;
        try {
            r = new java.io.BufferedReader(new java.io.FileReader(inputFile));
            Instances instances = new Instances(r);
            instances.setClassIndex(instances.numAttributes() - 1);
            LinearRegression linearRegression = new LinearRegression();
            linearRegression.buildClassifier(instances);
            double[] coef = linearRegression.coefficients();
            double myHouseValue = (coef[0] * 3198) + (coef[1] * 9669)
                    + (coef[2] * 5) + (coef[3] * 3) + (coef[4] * 1) + coef[6];
            System.out.println(Arrays.toString(coef));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	
	
	
	public static void TestHousePrice(){
		String inputFile = "D:/Program Files (x86)/Weka-3-6/data/house.arff";
        java.io.Reader r;
        try {
            r = new java.io.BufferedReader(new java.io.FileReader(inputFile));
            Instances instances = new Instances(r);
            instances.setClassIndex(instances.numAttributes() - 1);
            LinearRegression linearRegression = new LinearRegression();
            linearRegression.buildClassifier(instances);
            double[] coef = linearRegression.coefficients();
            System.out.println(Arrays.toString(coef));
            double myHouseValue = (coef[0] * 3198) + (coef[1] * 9669)
                    + (coef[2] * 5) + (coef[3] * 3) + (coef[4] * 1) + coef[6];
            System.out.println(myHouseValue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
