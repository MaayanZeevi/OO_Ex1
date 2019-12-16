package myMath;

import java.awt.*;
//import com.google.gson.Gson;
import java.io.*;
import java.util.Scanner;

import com.google.gson.Gson;

import java.util.*;
import  myMath.ComplexFunction;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Functions_GUI implements functions{

	LinkedList <function>ls;
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
			Color.red, Color.GREEN, Color.PINK};


	public Functions_GUI () {
		ls=new LinkedList<function>();
	}

	@Override
	public int size() {
		return ls.size();

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return ls.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return ls.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return ls.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return ls.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return ls.toArray(a);
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return ls.add(e);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return ls.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return ls.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return ls.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return ls.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return ls.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		ls.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		if(ls==null)this.ls=(new Functions_GUI()).ls;
		String line="";
		 try 
	        {
	        	BufferedReader br = new BufferedReader(new FileReader(file));
	        	
	            while ((line = br.readLine()) != null) 
	            {
	            	line=line.toLowerCase();
	              if(line.charAt(0)>='a'&&line.charAt(0)<='z') {
	            	  if(line.charAt(0)!='x') {
	            		  ComplexFunction temp=new ComplexFunction(line);
	            		  ls.add(temp);
	            		  continue;
	            	  }
	            }
	              
	            Polynom temp1=new Polynom(line);
	            ls.add(temp1);
	              }

	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            System.out.println("could not read file");
	        }

	    }

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		Iterator i=this.iterator();
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));

			StringBuilder sb = new StringBuilder();
			while(i.hasNext()) {
				function temp=(function) i.next();
				sb.append(temp.toString());
				sb.append("\n");
			}

			pw.write(sb.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
	}

	@Override
	public  void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.0025);
		StdDraw.line(rx.get_min(),0,rx.get_max(),0);
		StdDraw.line(0,ry.get_min(),0,ry.get_max());
		StdDraw.setPenColor(Color.black);
		double step= ((rx.get_max()-rx.get_min())/resolution);
		setnumberX( rx); 
		setnumberY(ry);
		Iterator i=this.iterator();
		int changecolor=0;
		while(i.hasNext()) {
			StdDraw.setPenColor(Colors[changecolor%7]);
			function temp=(function)i.next();
			for (double j = rx.get_min(); j <= rx.get_max(); j=j+step) {
				StdDraw.line(j,temp.f(j),j+step,temp.f(j+step));	
			}
			changecolor++;
		}
	}



	public static void setnumberX(Range rx) {
		for (int i = (int) rx.get_min(); i <= (int)rx.get_max(); i++) {
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.setPenRadius(0.005);
			String ans = "" + i;
			StdDraw.text(i,0.3,ans);
		}
	}

	public static void setnumberY(Range ry) {
		for (int i = (int) ry.get_min(); i <= (int)ry.get_max(); i++) {
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.setPenRadius(0.005);
			String ans = "" + i;
			StdDraw.text(0.3,i,ans);
		}
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
		Gson gson=new Gson();
		try {
			FileReader reader = new FileReader(json_file);
			paramater p = gson.fromJson(reader,paramater.class);
			Range x=new Range(p.Range_X[0],p.Range_X[1]);
			Range y=new Range(p.Range_Y[0],p.Range_Y[1]);
			this.drawFunctions(p.Width,p.Height,x,y,p.Resolution);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
        	
        }
	}

	
	public class paramater {
		int Width;
		int Height;
		int Resolution;
		double [] Range_X;
		double [] Range_Y;
	}



}
