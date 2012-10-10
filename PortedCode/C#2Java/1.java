package DvdCopyWord.WindowsAPI;

// ********* THIS FILE IS AUTO PORTED FROM C# USING CODEPORTING.COM *********

import com.codeporting.csharp2java.System.GC;


public class TestInheritence extends ParentTestClass //Sheraz Test1: Inheritance conversion in JAVA
{
	private String csTestStringVariable;
	private String csTestStringVariable1;
	private String csTestStringVariable2;
	
	public TestFunction testFunction()
	{

		DirectoryInfo di = new DirectoryInfo("Sample path here");;
		DirectoryInfo parent = Directory.GetParent(di.FullName);
		String newPath = Path.Combine(parent.FullName, "sample input path");

		// rename to some temp name, to help change lower and uppercast names
		di.MoveTo(com.codeporting.csharp2java.System.msString.concat(newPath,  "__renameTemp__"));
		di.MoveTo(newPath);

		// Trying to cleanup to prevent directory locking, doesn't work...
		di = null;
		parent = null;
		GC.collect(GC.MaxGeneration, GCCollectionMode.Forced);

                    GC.collect(GC.MaxGeneration, GCCollectionMode.Forced);
                    GC.collect(GC.MaxGeneration, GCCollectionMode.Forced);
                    GC.collect(GC.MaxGeneration, GCCollectionMode.Forced);


	}

	private Static Integer private TestFunc2testFunc2()
	{
		String s= cspath != null ? cspath :"";  //Sheraz Test2: Null operation transaltion in Java
	//test	string s= cspath??string.Empty;
	/*
			Integer? count= new Integer (); 
			string s= cspath??string.Empty;  //Sheraz test3: Comment transation check, should not be converted.
			
		*/
	return count;
	}

	protected String testFunc3()
	{

		
	
	    Parallel.ForEach(Partitioner.Create(source, EnumerablePartitionerOptions.NoBuffering),item =>
	    {
	        // ... process item
	    });
	
	    return "Helo World";
		//	Lets try this

	}

}

