package baby_names_miniProject;

import org.apache.commons.csv.CSVRecord;

import edu.duke.*;

/*
 * ABOUT THIS PROJECT:
 * 
 * This is one of the Coursera mini-projects. It finds what a persons name
 * would be if they were born in another year, based on the ranking(popularity) of 
 * their name in the year they were born. User can check what name they would of 
 * had in years from 1880 to 2014. CSV files are from the US Government.
 * 
 * @author Zev
 * 
 * @since 28/03/2018
 */



public class BabyBirths {

	
	public void printNames(int year, String name, String gender) {
		
		String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";

		FileResource fr = new FileResource(filePath);
		
		//False means this CSV does NOT have a header row
		for(CSVRecord record : fr.getCSVParser(false)) {
			
			if(Integer.parseInt(record.get(2)) < 100) {
				
				System.out.println("Name: " + record.get(0) 
								+ " Gender: " + record.get(1)
								+ " Number Born: " + record.get(2));
			}
		}
	}
	
	
	/*
	 * Finds total births in a year
	 */
	public void totalBirths(int year) {
		
		String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";

		FileResource fr = new FileResource(filePath);
		
		int totalBirths = 0;
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			
			int numBorn = Integer.parseInt(record.get(2));
			totalBirths += numBorn;
		}
		
		System.out.println("total births in " + year + ": " + totalBirths);
	}
	
	
	/*
	 * Calculates number of boy names and girl names in a year
	 */
	public void totalNames(int year) {
		
		String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";

		FileResource fr = new FileResource(filePath);

		int totalBoyNames = 0;
		int totalGirlNames = 0;
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			
			if(record.get(1).equals("M")) {
				totalBoyNames++;
			}
			else {
				totalGirlNames++;
			}
		}
		System.out.println("Total number of boy names in " + year + " is " + totalBoyNames);
		System.out.println("Total number of girl names in " + year + " is " + totalGirlNames);
	}
	
	
	/*
	 * Rank is the name with the largest number of births. The CSV file ordering is based on rank.
	 * getRank method simple counts
	 */
	public int getRank(int year, String name, String gender) {
		
		int maleRank = 0;
		int femaleRank = 0;
		
		String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
		
		FileResource fr = new FileResource(filePath);
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			
			//count rank
			if(record.get(1).equals("M")) {
				maleRank++;
				if(record.get(0).equals(name) && record.get(1).equals(gender)) {
					//System.out.println("The rank of " + name + " in the year " + year +  " is: " + maleRank);
					return maleRank;
				}
			}
			else if(record.get(1).equals("F")) {
				femaleRank++;
				if(record.get(0).equals(name) && record.get(1).equals(gender)) {
					//System.out.println("The rank of " + name + " in the year " + year +  " is: " + femaleRank);
					return femaleRank;
				}
			}
		}
		return -1; //Person NOT found
	}
	
	
	/*
	 * yearOfHighesRank checks records from 1880 to 2014 and identifies the year with the
	 *  highest rank of a particular name and gender and returns that year.
	 */
	public int yearOfHighestRank(String name, String gender) {
		
		int year = 1880; //first year to check
		int yearOfHighestRank = 0;
		int highestRank = -1; //Initialize with -2 since if no rank -1 is returned by getRank
		
		//CSV file cover span of 134 years from 1880 to 2014
		for(int i = 0; i < 134; i++) { 
						
			int currRank = getRank(year, name, gender);
			
			//Initial highest rank to be set here
			if(currRank != -1 && highestRank == -1) {
				highestRank = currRank;
				yearOfHighestRank = year;

			}
			else if(currRank != -1 && currRank < highestRank) { //Smaller number indicates name is higher on the list
				highestRank = currRank;
				yearOfHighestRank = year;
				//System.out.println(yearOfHighestRank);
			}
			year++;
		}
		if(highestRank == -1) {
			return -1;
		}
		else {
			System.out.println(); //spacing
			System.out.println("Highest rank of the name " + name + " is in the year " + yearOfHighestRank);
			return yearOfHighestRank;
		}
	}
	
	
	/*
	 * Finds average rank for a name from 1880 to 2014
	 */
	public double averageRank(String name, String gender) {
		
		int totalRank = 0;
		int year = 1880;
		
		//CSV file cover span from 1880 to 2014, thats 135 files
		for(int i = 0; i < 134; i++) {
			year++;
			int currRank = getRank(year, name, gender);
			if(currRank == -1) return -1.0; //Don't computer average if no name found in a particular year
			else totalRank += currRank;

			//year++; //Must put last otherwise skips year 1880
		}
		double avgRank = (double)totalRank / 134.0; //135 files
		return avgRank;
	}
	
	
	/*
	 * This method returns an integer, the total number of births of 
	 * those names with the same gender and same year who are ranked higher than 
	 * parameter input 'name'. 
	 */
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		
		int rank = getRank(year, name, gender); 
		
		String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
		FileResource fr = new FileResource(filePath);
		
		int totalBirths = 0;
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			
			int currRank = getRank(year, record.get(0), gender);
			
			if(record.get(1).equals(gender) && currRank < rank) {
				int numBorn = Integer.parseInt(record.get(2));
				totalBirths += numBorn;
			}
		}
		return totalBirths;
	}
	

	/*
	 * Find the name based on the year, rank and gender
	 */
	public String getName(int year, int rank, String gender) {
		
		int maleCount = 0; //counter used to keep track in file
		int femaleCount = 0;
		
		String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
		
		FileResource fr = new FileResource(filePath);
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			
			if(record.get(1).equals("M")) {
				maleCount++;
				if(rank == maleCount) {
					return record.get(0); //return name
				}
			}
			else if(record.get(1).equals("F")) {
				femaleCount++;
				if(femaleCount == rank && record.get(1).equals(gender)) {
					return record.get(0);
				}
			}
		}
		return "NO NAME"; //rank does NOT exist in the file
	}
	
	
	/*
	 * seachName searches through file and terms if 'name' is in it
	 */
	
	public void searchName(int year, String name, String gender) {
		
		String filePath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
		
		FileResource fr = new FileResource(filePath);
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			if(record.get(0).equals(name) && record.get(1).equals(gender)) {
				System.out.println("NAME FOUND");
				return;
			}
		}
		System.out.println("NAME NOT FOUND");
		return; 
	}

	/*
	 * This method finds the rank of ones name in the year they
	 * were born. Then determines what their name would be in 
	 * the year XXXX by matching the rank number in that year
	 * 
	 * name: is name of person
	 * year: is the year that person was born
	 * newYear: is the year to identify what their name would have been
	 * gender: M or F
	 * 
	 */
	public String whatIsNameInYear(String name, int year, int newYear, String gender) {
		
		// Ensures first letter is capital.
		String properCaseName = ("" + name.charAt(0)).toUpperCase() + name.substring(1, name.length());

		int rank = getRank(year, properCaseName, gender.toUpperCase());
		String newName = getName(newYear, rank, gender.toUpperCase());
		
		
		if(gender.toUpperCase().equals("M")) {
			String s = properCaseName + " born in " + year 
					+ " would be " + newName + " if he was born in " + newYear;
			return s;
		}
		else if(gender.toUpperCase().equals("F")){
			String s = properCaseName + " born in " + year 
					+ " would be " + newName + " if she was born in " + newYear;
			return s;
		}
		else {
			return "Invalid gender";
		}
	}
}
