package com.demo.DataMiningAssigment2;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Math ;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Kmeans {
	public int [][] points = new int[150][20] ;
	public void read_from_excel_file(String excelfilename)
	{
		try { 
	        FileInputStream file = new FileInputStream(new File(excelfilename));  
	        XSSFWorkbook workbook = new XSSFWorkbook(file);  
	        XSSFSheet sheet = workbook.getSheetAt(0);  
	        Iterator<Row> rowIterator = sheet.iterator(); 
	        rowIterator.next();
	        int j =0 ;
	        while (rowIterator.hasNext()) { 
	            Row row = rowIterator.next(); 
	            int [] arr =new int[20] ;
	            Iterator<Cell> cellIterator = row.cellIterator(); 
	            cellIterator.next();
	            int counter = 0 ;
	            while (cellIterator.hasNext()) { 
	             Cell cell = cellIterator.next(); 
	             arr[counter]=(int) cell.getNumericCellValue();
	            counter ++ ;
	            }
	            	for(int i=0;i<20;++i)
	            	{
	            		points[j][i] = arr[i] ;
	            	}
	            	j++ ;
	            
	        } 
	        workbook.close();
	        file.close();
	    } 
	    catch (Exception e) { 
	        e.printStackTrace(); 
	    }	
	}
	public void Groupintoclusters(int numberofclusters)
	{
		int [][][] clusters = new int [numberofclusters][151][21] ;
		double[][] average = new double [numberofclusters][20] ;
		for(int i=0;i<numberofclusters;i++) {
			Random generator = new Random();
			int randomIndex = generator.nextInt(points.length);
			for(int k=0;k<20;++k)
			{
					clusters[i][0][k] = points[randomIndex][k];	
			}
		}
		double sum = 0 ; 
		double min = 2147483647 ;
		int counter =0 ; 
		double Euclideandist = 0 ;
		int x =1 ;
		for(int i = 0 ; i< 150 ; ++i )
		{
			min = 2147483647;
			for(int j=0;j<numberofclusters;j++)
			{
				sum = 0 ;
				for(int k=0;k<20;++k)
				{
					sum += ((points[i][k]-clusters[j][0][k])^2);
				}
				Euclideandist = Math.sqrt(sum) ;
				if(Euclideandist <min)
				{
					min=Euclideandist ;
					counter = j ;
				}
			}
			for(int k=0;k<20;++k)
			{
				clusters[counter][x][k] = points[i][k] ;
			}
			x++ ;
			
		}			
		while(true)
		{
			int [][][] newclusters = new int [numberofclusters][151][21] ;
			for(int i = 0 ; i< numberofclusters ; ++i )
			{
				for(int j=0;j<151;j++)
				{
					for(int k=0 ;k<21;++k)
					{
							newclusters[i][j][k] = clusters[i][j][k] ;
							
					}
				
				}
			}
			double newsum = 0 ;
			double avg = 0 ;
			int countofpoints = 0;
			for(int i=0;i<numberofclusters;++i) 
			{
				for(int j=0;j<20 ;++j )
				{
					newsum=0 ;
					countofpoints = 0;
					for(int k=0;k<150;++k )
					{
						if(clusters[i][k][j]!=0)
						{
							newsum += clusters[i][k][j] ;
							countofpoints++;
						}
							
					}
					avg = newsum /countofpoints  ;
					average[i][j] = avg ; 
				}
			}
			double currsum = 0 ;
			double newmin =2147483647 ;
			int num = 1 ;
			for(int i = 0 ; i< 150 ; ++i )
			{
				newmin =2147483647 ;
				for(int j=0;j<numberofclusters;j++)
				{
					currsum=0;
					for(int k=0;k<20;++k)
					{
							currsum += (points[i][k]-(int)average[j][k])^2;	
						
					}
					Euclideandist = Math.sqrt(sum) ;
					if(Euclideandist <newmin)
					{
						newmin=Euclideandist ;
						counter = j ;
					}
				}
				for(int k=0;k<20;++k)
				{
					clusters[counter][num][k] = points[i][k] ;
				}
				num++;
				
			}
			boolean check = false ;
			for(int i = 0 ; i< numberofclusters ; ++i )
			{
				for(int j=0;j<151;j++)
				{
					for(int k=0 ;k<21;++k)
					{
							if(newclusters[i][j][k]!=clusters[i][j][k])
							{
								check = true ;
								break ;
							}
							
					}
					if(check ==true)
					{
						break ;
					}
				
				}
				if(check==true)
				{
					break ;
				}
				
			}
			if(check==true)
			{
				continue ;
			}
			else
			{
				break ;
			}

			
		}
		boolean flag=false ;
		for(int i = 0 ; i< numberofclusters ; ++i )
		{
			int y=i+1;
			System.out.print("cluster" + y +" has the follwing "+"\n"+"--------------------------------------------------------------------"+"\n");
			
			for(int j=0;j<151;j++)
			{
				for(int k=0 ;k<20;++k)
				{
					if(clusters[i][j][k]!=0 )
					{
						System.out.print(clusters[i][j][k]+" ");
						flag=true ;
					}
					else
					{
						flag=false ;
						break ;
					}
						
				}
				if(flag == true )
				{
					System.out.print("\n");
				}
				flag=false ;
			}
			System.out.print("--------------------------------------------------------------------"+"\n");
		}
	}
}
