package tutorial;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.IMethodCoverage;
import org.jacoco.core.analysis.ICounter;
import org.jacoco.core.data.ExecutionData;
import org.jacoco.core.data.ExecutionDataReader;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.IExecutionDataVisitor;
import org.jacoco.core.data.ISessionInfoVisitor;
import org.jacoco.core.data.SessionInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;



public class convertExec{


    public static void main(String args[]) throws IOException {
	String outputDir = "coverage/";
	//String targetClass = "/home/liam/REUSE/Tutorial_Stack/src/main/java/tutorial/Stack.java";
	String targetClass = "/home/liam/REUSE/Tutorial_Stack/target/classes/tutorial/Stack.class";
	//	String pathToCoverageClass = outputDir + File.separator
	//+ "coverage/coverage.out" + File.separator + targetClass;

	String jacocoPath = "/home/liam/REUSE/Tutorial_Stack/target/jacoco.exec";
	String pathToCoverageClass = targetClass;

	//ExecutionDataStore executionData = null;

	//if (executionData == null) {
	final ExecutionDataStore executionData = new ExecutionDataStore();
	//}

	final FileInputStream in = new FileInputStream(new File(
								jacocoPath));
	final ExecutionDataReader reader = new ExecutionDataReader(in);
	reader.setSessionInfoVisitor(new ISessionInfoVisitor() {
		public void visitSessionInfo(final SessionInfo info) {
		}
	    });
	reader.setExecutionDataVisitor(new IExecutionDataVisitor() {
		public void visitClassExecution(final ExecutionData data) {
		    executionData.put(data);
		}
	    });

	reader.read();
	in.close();
	System.out.println("Marker1");
	final CoverageBuilder coverageBuilder = new CoverageBuilder();
	final Analyzer analyzer = new Analyzer(executionData,
					       coverageBuilder);
	analyzer.analyzeAll(new File(pathToCoverageClass));
	//TreeSet<XXXX> coveredMethods = new TreeSet<XXXX>();
	System.out.println("Marker2");
	System.out.println(coverageBuilder.getClasses().toString());
	for (final IClassCoverage cc : coverageBuilder.getClasses()) {
	    System.out.println("Marker3");
	    for (final IMethodCoverage meth : cc.getMethods() ){
		System.out.println("Marker4");
		//In the future, this is where we'll do feature extraction.
		//For now, getting a printed result will be more than enough


		//new methodDataPoint newMeth;
		//newMeth.setSig(meth.getSignature());
		//newMeth.setStatus(meth.getStatus());
		System.out.println("Description:\t" + meth.getDesc());
		System.out.println("Signature:\t" + meth.getSignature());
		System.out.println("Status:\t" + meth.getMethodCounter().getStatus());
		System.out.println("\n");
	    }

	    /*for (int i = cc.getFirstLine(); i <= cc.getLastLine(); i++) {
	      boolean covered = false;
	      switch (cc.getLine(i).getStatus()) {
	      case ICounter.PARTLY_COVERED:
	      covered = true;
	      break;
	      case ICounter.FULLY_COVERED:
	      covered = true;
	      break;
	      case ICounter.NOT_COVERED:
	      break;
	      case ICounter.EMPTY:
	      break;
	      default:
	      break;
	      }
	      if (covered) {
	      coveredLines.add(i);
	      }
	      }
	      }*/

	}

    }
}








