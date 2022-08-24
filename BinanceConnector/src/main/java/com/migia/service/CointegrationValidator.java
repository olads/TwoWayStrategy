package com.migia.service;



import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CointegrationValidator {


    public String isCointegrated(double[] pairA,double[] pairB){
		/*
		 * var value = new CointegrationMLE(new
		 * MultivariateSimpleTimeSeries(pairA,pairB),false); var res = new
		 * JohansenTest(JohansenAsymptoticDistribution.Test.EIGEN,
		 * JohansenAsymptoticDistribution.TrendType.CONSTANT_TIME,5); var R =
		 * res.r(value,0.95); System.out.println("The value for R is " + R);
		 * 
		 * return "The value for R is " + R;
		 */
    	return null;
    }

}
