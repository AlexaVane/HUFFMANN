package Algoritmo_Huffman;

import java.util.StringTokenizer;

public class HuffmanArreglos 
{
	public static void main (String ars[])
	{
		Object[][] objDatosOriginales ={{"a","0.3"},{"b","0.2"},{"c","0.5"},{"d","0.4"},{"e","0.1"},{"f","0.2"}};
		//Object[][] objDatosOriginales ={{"a","0.4"},{"b","0.1"},{"c","0.1"},{"d","0.3"},{"e","0.1"}};
		//Object[][] objDatosOriginales ={{"a","0.4"},{"b","0.2"},{"c","0.1"},{"d","0.1"},{"e","0.1"},{"f","0.1"}};
		//Object[][] objDatosOriginales ={{"a","0.5"},{"b","0.25"},{"c","0.25"},{"d","0.1"},{"e","0.025"}};
				/*Object [][]objDatosOriginales={{"a","0.1253"},{"b","0.0142"},
				{"c","0.0468"},{"d","0.0586"},
				{"e","0.1368"},{"f","0.0069"},
				{"g","0.0468"},{"h","0.0586"},
				{"i","0.0625"},{"j","0.0044"},
				{"k","0.0001"},{"l","0.0497"},
				{"m","0.0315"},{"n","0.0671"},
				{"ñ","0.0031"},{"o","0.0868"},
				{"p","0.0251"},{"q","0.0088"},
				{"r","0.0687"},{"s","0.0798"},
				{"t","0.0463"},{"u","0.0393"},
				{"v","0.009"},{"w","0.0002"},
				{"x","0.0022"},{"y","0.009"},
				{"z","0.0052"}	
		};*/

		System.out.println("****Arreglo Original****");
		Imprimir(objDatosOriginales);

		System.out.println("****Arreglo Original Ordenado****");
		objDatosOriginales=Burbuja(objDatosOriginales, 1);
		Imprimir(objDatosOriginales);

		Object[][] objDatosIniciales = CopiarArreglo(objDatosOriginales);

		System.out.println("****Arreglo Preparado Codificar");
		PrepararArregloCodificar(objDatosIniciales);
		Imprimir(objDatosIniciales);

		int intAscii=65, intPaso=1;
		System.out.println("\n\n****Proceso de Codificicación****");
		Object[][] objCodificado=Codificar(objDatosIniciales,intAscii,intPaso);

		System.out.println("\n\n****Objeto Codificado****");
		Imprimir(objCodificado);

		System.out.println("****Arreglo Preparado Decodificar****");
		Object[] objBinario={"1","0"};
		objDatosIniciales=PrepararArregloDecodificar(objCodificado, objBinario);
		Imprimir(objDatosIniciales);

		System.out.println("\n\n****Proceso de Decodificación****");
		Object[][] objDecodificado=Decodificar(objDatosIniciales,objBinario,intAscii+objDatosOriginales.length-3,intPaso,objDatosOriginales.length);

		System.out.println("\n\n****Objeto Decodificado****");
		Imprimir(objDecodificado);

		System.out.println("\n\n****Objeto Final***");
		for(int i=0;i<objDatosOriginales.length;i++)
		{
			System.out.println(objDatosOriginales[i][0] + "\t"+objDecodificado[i][0]+"\t"+objDecodificado[i][1]);
		}
	}

	public static void Imprimir(Object[][] objArreglo)
	{
		for(int i=0; i<objArreglo.length;i++)
		{
			for(int j=0; j<objArreglo[i].length;j++)
			{
				System.out.print(objArreglo[i][j]+"\t\t\t\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void  PrepararArregloCodificar(Object[][] objArreglo)
	{
		for(int i=0;i<objArreglo.length;i++)
		{
			for(int j=0;j<objArreglo[i].length;j++)
			{
				objArreglo[i][0]=objArreglo[i][1];
				objArreglo[i][1]=objArreglo[i][1];
			}
		}
	}

	public static Object[][] Codificar (Object[][] objArreglo, int intAscii, int intContador)
	{
		if(objArreglo.length>2)
		{
			Object[][] objArregloAuxiliar=new Object[objArreglo.length-1][2];
			int i=0;
			for(i=0;i<(objArreglo.length-2);i++)
			{
				for(int j=0;j<objArreglo[i].length;j++)
				{
					objArregloAuxiliar[i][j]=objArreglo[i][j];
				}
			}

			objArregloAuxiliar[i][0]=Double.parseDouble(objArreglo[i][0].toString()) + Double.parseDouble(objArreglo[i+1][0].toString());
			objArregloAuxiliar[i][1]= (char)intAscii + objArreglo[i][1].toString()+(char)intAscii + objArreglo[i+1][1].toString();

			objArregloAuxiliar=Burbuja(objArregloAuxiliar,0);

			System.out.println("****Codificación Paso-->"+intContador+"****");
			Imprimir(objArregloAuxiliar);

			return Codificar(objArregloAuxiliar,++intAscii,++intContador);
		}
		return objArreglo;
	}

	public static Object[][] Burbuja(Object[][] objArreglo, int intPosicion)
	{
		boolean blnVerificar = true;  
		Object[] objTemporal= new Object[2];

		while (blnVerificar)
		{
			blnVerificar=false;    
			for(int j=0;j<objArreglo.length-1;j++)
			{
				if(Double.parseDouble(objArreglo[ j ][intPosicion].toString())<Double.parseDouble(objArreglo[j+1][intPosicion].toString()) ) 
				{
					objTemporal[0] = objArreglo[ j ][0]; 
					objTemporal[1] = objArreglo[j][1];

					objArreglo[ j ][0] = objArreglo[ j+1 ][0];
					objArreglo[ j ][1] = objArreglo[ j+1 ][1];

					objArreglo[ j+1 ][0] = objTemporal[0];
					objArreglo[ j+1 ][1] = objTemporal[1];

					blnVerificar = true;           
				} 
			} 
		} 
		return objArreglo;
	}

	public static Object[][] PrepararArregloDecodificar(Object[][] objArreglo,Object[] objBinario)
	{
		for(int i=0;i<objArreglo.length;i++)
		{
			objArreglo[i][0]=objArreglo[i][1];
			objArreglo[i][1]=objBinario[i];	
		}
		return objArreglo;
	}

	public static Object[][] Decodificar (Object[][] objArreglo,Object[] objBinario, int intAscii,int intPaso,int intTamaño)
	{
		if(objArreglo.length<intTamaño)
		{
			Object[][] objArregloAuxiliar=new Object[objArreglo.length+1][2];
			int d=0,f=0;

			for(d=0;d<objArreglo.length;d++)
			{
				if(String.valueOf(objArreglo[d][0]).substring(0,1).equals(String.valueOf((char)intAscii)))
				{
					f=d;
					break;
				}
			}

			for(d=0;d<objArreglo.length-1;d++)
			{

				if(d<f)
				{
					objArregloAuxiliar[d][0]=objArreglo[d][0];
					objArregloAuxiliar[d][1]=objArreglo[d][1];
				}
				else
				{
					objArregloAuxiliar[d][0]=objArreglo[d+1][0];
					objArregloAuxiliar[d][1]=objArreglo[d+1][1];
				}
			}


			StringTokenizer strTknPartes = new StringTokenizer(objArreglo[f][0].toString(),String.valueOf((char)intAscii)); 

			System.out.println((char)intAscii + objArreglo[f][0].toString());
			if(strTknPartes.hasMoreTokens())
			{	
				objArregloAuxiliar[d][0]=strTknPartes.nextToken();
				objArregloAuxiliar[d][1]=objArreglo[f][1] + objBinario[0].toString();

				if(strTknPartes.hasMoreTokens())
				{	
					objArregloAuxiliar[d+1][0]=strTknPartes.nextToken();
					objArregloAuxiliar[d+1][1]=objArreglo[f][1]+objBinario[1].toString();
				}
			}

			System.out.println("****Decodificación Paso: "+intPaso+"****");
			Imprimir(objArregloAuxiliar);

			return Decodificar(objArregloAuxiliar,objBinario,--intAscii,++intPaso,intTamaño);
		}
		return objArreglo;
	}

	public static Object[][] CopiarArreglo(Object[][] objArreglo)
	{
		Object[][] objCopia=new Object[objArreglo.length][objArreglo[0].length];
		for(int i=0;i<(objArreglo.length);i++)
		{
			for(int j=0;j<objArreglo[i].length;j++)
			{
				objCopia[i][j]=objArreglo[i][j];
			}
		}
		return objCopia;
	}
}
