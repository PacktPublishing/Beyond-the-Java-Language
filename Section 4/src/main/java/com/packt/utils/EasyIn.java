// Simple input from the keyboard for all primitive types. ver 1.0
// Copyright (c) Peter van der Linden,  May 5 1997.
//     corrected error message 11/21/97
//
// The creator of this software hereby gives you permission to:
//  1. copy the work without changing it
//  2. modify the work providing you send me a copy which I can
//     use in any way I want, including incorporating into this work.
//  3. distribute copies of the work to the public by sale, lease, 
//     rental, or lending
//  4. perform the work
//  5. display the work
//  6. fold the work into a funny hat and wear it on your head.
//
// This is not thread safe, not high performance, and doesn't tell EOF.
// It's intended for low-volume easy keyboard input.
// An example of use is:
//     EasyIn easy = new EasyIn();
//     int i = easy.readInt();   // reads an int from System.in
//     float f = easy.readFloat();   // reads a float from System.in

package com.packt.utils;
import java.io.*;
import java.util.*;

public class EasyIn
{
	static InputStreamReader is = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(is);
	StringTokenizer st;

	StringTokenizer getToken() throws IOException
	{
		String s = br.readLine();
		return new StringTokenizer(s);
	}

	@SuppressWarnings("deprecation")
	public boolean readBoolean()
	{
		try
		{
			st = getToken();
			return new Boolean(st.nextToken()).booleanValue();
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readBoolean");
			return false;
		}
	}

	public byte readByte()
	{
		try
		{
			st = getToken();
			return Byte.parseByte(st.nextToken());
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readByte");
			return 0;
		}
	}

	public short readShort()
	{
		try
		{
			st = getToken();
			return Short.parseShort(st.nextToken());
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readShort");
			return 0;
		}
	}

	public int readInt()
	{
		try
		{
			st = getToken();
			return Integer.parseInt(st.nextToken());
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readInt");
			return 0;
		}
	}

	public long readLong()
	{
		try
		{
			st = getToken();
			return Long.parseLong(st.nextToken());
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readLong");
			return 0L;
		}
	}

	public float readFloat()
	{
		try
		{
			st = getToken();
			return new Float(st.nextToken()).floatValue();
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readFloat");
			return 0.0F;
		}
	}

	public double readDouble()
	{
		try
		{
			st = getToken();
			return new Double(st.nextToken()).doubleValue();
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readDouble");
			return 0.0;
		}
	}

	public char readChar()
	{
		try
		{
			String s = br.readLine();
			return s.charAt(0);
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readChar");
			return 0;
		}
	}

	public String readString()
	{
		try
		{
			return br.readLine();
		} catch (IOException ioe)
		{
			System.err.println("IO Exception in EasyIn.readString");
			return "";
		}
	}
}
