package weka.demo;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instances;

public class FeatureSelection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//根据属性名
	//model 为特征选择后的训练集，origin为原始的测试集
	public static Instances delAttr(Instances model, Instances origin){
	    boolean flag = false;
	    ArrayList<Integer> al = new ArrayList<Integer>();
	    for(int q = 0; q < origin.numAttributes() - 1; q++){
	      String temp2 = origin.attribute(q).name();
	      for(int x = 0; x < model.numAttributes() - 1; x++){
	        String temp1 = model.attribute(x).name();  
	        if(temp1.equals(temp2)){
	          flag = true;
	          break;
	        }
	      }
	      if(flag){
	        flag = false;
	        continue;
	      }else
//			dataCopy.deleteAttributeAt(q);   //you can not do like this
	        al.add(new Integer(q));
	    }
	    
	    for(int q = 0; q < al.size(); q++){
	      int deltemp = al.get(q) - q;		// pay attention to this line
	      origin.deleteAttributeAt(deltemp);
	    }
	    return origin;
	  }
	
	//model 为特征选择后的训练集，process为特征选择后delAttr()返回的测试集，但两者的属性排序可能不一样
	public static Instances sort(Instances model, Instances process){
	    Attribute attr;
	    for(int i = 0; i < model.numAttributes()-1; i++){
	      for(int j = 0; j < process.numAttributes()-1; j++){
	        if(process.attribute(j).name().equals(model.attribute(i).name())){
	          if(j!=i){
		           attr = process.attribute(j);
		           process.insertAttributeAt(attr, i);//插入属性
		           
		           //插入属性值
		           for(int k = 0; k < process.numInstances(); k++){
		             process.instance(k).setValue(i, process.instance(k).stringValue(j+1));	//pay attention to j+1
		           }
		           break;
	          }
	        }
	      }
	    }
	    //删除
	    for(int i = process.numAttributes() - 2; i > model.numAttributes()-2; i--){
	      process.deleteAttributeAt(i);;
	    }
	    return process;
	}
	
	//根据属性下标，al为从小到大排序
	public static Instances delAttr(ArrayList<Integer> al, Instances inst){
	    for(int i = inst.numAttributes() - 1; i > -1; i--){	//delete from back to forward
	      if(!al.contains(i))
	        inst.deleteAttributeAt(i);
	    }	    
	    return inst;
	  }

}
