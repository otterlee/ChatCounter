package edu.handong.csee.java.hw3;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * CLIOption is the class that set the CLI option.
 * inputPath is the path that contains files to read.
 * outputPath is the path where the new file will be written.
 * inputNumThread is the number of threads.
 * @author suagu
 *
 */

public class CLIOption {

	String inputPath, outputPath;
	int inputNumThread;
	boolean verbose;
	boolean help;

	/**
	 * run is the main method in this class.
	 * use create Option to create my own options.
	 * and use parseOption method to parse option.
	 * 
	 */
	public void run(String[] args) {
		Options options = createOptions();

		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}

		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			inputPath = cmd.getOptionValue("i");
			outputPath = cmd.getOptionValue("o");
			inputNumThread = Integer.parseInt(cmd.getOptionValue("c"));
			help = cmd.hasOption("h");
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}

	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set a path of a directory which contains input files")
				.hasArg()
				.argName("the input directory path")
				.required()
				.build());

		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set a file name with path to save output files")
				.hasArg()
				.argName("the output directory path")
				.required()
				.build());
		options.addOption(Option.builder("c").longOpt("numthreads")
				.desc("Set the number of threads")
				.hasArg()
				.argName("the number of thread")
				.required()
				.build());


		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());

		return options;
	}

	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "help _ChatCounter ";
		String footer ="\nPlease report issues at https://github.com/otterlee/ChatCounter/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

}
