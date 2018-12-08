package Fonctions;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.Scanner ;
public class limite {
	private final double x;
	private final Expression op;
	private final int PRECISION = 7;
	private final double ABS_SCRAP = 0.1;
	private DecimalFormat d = new DecimalFormat("#.####");
	
	public limite(double x, Expression op){
		if (op == null) throw new IllegalArgumentException();
		this.x = x;
		this.op = op;
		d.setRoundingMode(RoundingMode.CEILING);
	}
	
	public double leftLimit(){
		double round = x - 0.1;
		Double eval = null;
		
		for (int i = 1; i != PRECISION; i++){
			try
			{
				eval = op.setVariable("x", round).evaluate();
				round = x - (0.1 / Math.pow(10, i));
			}
			catch (Exception e) { break; }
		}
		
		return eval;
	}
	
	public double rightLimit(){
		double round = x + 0.1;
		Double eval = null;
		
		for (int i = 1; i != PRECISION; i++){
			try
			{
				eval = op.setVariable("x", round).evaluate();
				round = x + (0.1 / Math.pow(10, i));
			}
			catch (Exception e) { break; }
		}
		
		return eval;
	}
	
	public boolean exists(){
		return (Math.abs(leftLimit()) - Math.abs(rightLimit()) < ABS_SCRAP);
	}
	
	public String evalLimit(){
		if (!exists()) throw new IllegalStateException("Limit does not exist");
		double left = leftLimit();
		double right = rightLimit();
		return d.format((left + right)/2);
	}
	
	public static void main(String[] args){
		System.out.println("ecrire la fonction");
		
		Scanner scan = new Scanner (System.in);
		String expr = scan.nextLine();
		ExpressionBuilder exp = new ExpressionBuilder(expr);
		exp.variable("x");
		System.out.print("x===>");
		String str = scan.nextLine();
		double varr = 0;
		if (str.equalsIgnoreCase("+inf") )
		{
			  varr=999999;
			  
		}
		else if (str.equalsIgnoreCase("-inf") )
		{
			  varr=-999999;
			  
		}
		else if (str.equalsIgnoreCase("e") )
		{
			  varr= Math.exp(1);
			  
		}
		else if (str.equalsIgnoreCase("-e") )
		{
			  varr= -Math.exp(1);
			  
		}
		else if (str.equalsIgnoreCase("pi") )
		{
			  varr= Math.PI;
			  
		}
		else if (str.equalsIgnoreCase("-pi") )
		{
			  varr= -Math.PI;
			  
		}
		
		else
		{
			varr= Double.valueOf(str);
		}
		
		limite lim = new limite(varr, exp.build());
		String limite = lim.evalLimit();
		double ll = Double.valueOf(limite);
		if (ll > 9000)
		{
			limite = "+inf";
		}
			
		
		System.out.println(limite); 
	}
	
}
