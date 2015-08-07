package demo.cluster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import weka.clusterers.XMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/** *//**
 * @author Jia Yu
 * @date 2010-5-28
 */
public class WekaCluster2 {

    /** *//**
     * @param args
     */

    private ArffLoader loader;
    private Instances dataSet;
    private XMeans cluster;
    private int numOfClusters;
    private String newAttribute;
    private File arffFile;
    private int sizeOfDataset;

    public WekaCluster2(File arffFile) {
    this.arffFile = arffFile;
    	doCluster();
}

    private void doCluster() {
        loader = new ArffLoader();
        newAttribute = "";
        try {
            loader.setFile(arffFile);
            dataSet = loader.getDataSet();
            cluster = new XMeans();
            cluster.buildClusterer(dataSet);
            numOfClusters = cluster.numberOfClusters();
           StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numOfClusters; i++) {
               sb.append("s" + (i + 1) + " ");
}
           newAttribute = sb.toString().trim();
           sizeOfDataset = dataSet.numInstances();
        } catch (Exception e) {
            e.printStackTrace();
       }
   }

    public void newArffWriter() {
        int lineNum = 0;
       try {
          Scanner input = new Scanner(arffFile);
          	/*  
          	PrintWriter out = new PrintWriter(CfUtil
                    .GetFileNameNoExtFromFileName(arffFile.getName())
                    + "_classification.arff");
            */
            PrintWriter out = new PrintWriter((arffFile.getName())
                    + "_classification.arff");

           while (input.hasNext()) {
               String line = input.nextLine();
               if (line.startsWith("@relation")) {
                   out.println("@relation" + line.substring(9)
                          + "_classification");
               } else if (line.startsWith("@data")) {
                    out.println("@attribute shape {" + newAttribute + "}");
                   out.println("@data");
                } else if (line.startsWith("@attribute")) {
                   out.println(line);
                } else if (line.isEmpty()) {
                    out.println();
                } else {
                    line += ",class"
                            + (cluster.clusterInstance(dataSet
                                   .instance(lineNum)) + 1);
                   out.println(line);
                    lineNum++;
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public int clusterNewInstance(weka.core.Instance instance) {
        int indexOfCluster = -1;
       try {
            indexOfCluster = cluster.clusterInstance(instance);
           //System.out.println("demo.cluster " + indexOfCluster);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return indexOfCluster;
   }

   public double[] frequencyOfCluster() {
        int[] sum = new int[this.numOfClusters];
        try {
            for (int i = 0; i < this.sizeOfDataset; i++) {
                sum[cluster.clusterInstance(dataSet.instance(i))]++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        double[] fre = new double[sum.length];
        for (int i = 0; i < sum.length; i++) {
            fre[i] = (double)sum[i] / (double)this.sizeOfDataset;
        }
        return fre;
   }
/*
    public static void main(String[] args) {
        File file = new File("cpu.arff");
       WekaCluster wc = new WekaCluster(file);
        double[] fre = wc.frequencyOfCluster();
        for(int i=0;i<fre.length;i++)
            System.out.println(fre[i]);
        // wc.newArffWriter(file);
        double[] feature = { 125,256,6000,256,16,128,199 };
        weka.core.Instance ins = new weka.core.Instance(7);
        for (int i = 0; i < ins.numAttributes(); i++) {
            ins.setValue(i, feature[i]);
            // System.out.println(ins.attribute(i).getLowerNumericBound());
        }
        System.out.println("demo.cluster in : "+wc.clusterNewInstance(ins));
    }
*/
}
