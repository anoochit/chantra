import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Function {

	public static void createProjectFolder(String projectFolder) {
		try {
			String trunk = projectFolder + "/svn/trunk";
			String bin = trunk + "/bin";
			String disctree = trunk + "/disctree/en";
			String programs = trunk + "/programs";

			// Create multiple directories
			new File(trunk).mkdirs();
			new File(bin).mkdir();
			new File(disctree).mkdirs();
			new File(programs).mkdir();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void createProgramFolder(String config, String projectFolder)
			throws IOException {
		String programFolder = projectFolder + "/svn/trunk/programs";
		String listfile = config + "/programlist.txt";
		try {
			BufferedReader br = getNewBufferedReader(listfile);
			String program;
			while ((program = br.readLine()) != null) {
				new File(programFolder + "/" + program).mkdir();
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void update(String programdir, String configdir)
			throws IOException {
		String oldconfig = getOldConfig(programdir);
		String programname = getProgramname(programdir);
		BufferedReader newbr = getNewBufferedReader(configdir);

		String newconfig = newbr.readLine();
		hasConfig(newconfig, programname, newbr);

		newconfig = newbr.readLine();// this will be current version
		String newversion = newconfig;
		System.out.println("old version is : " + oldconfig);
		System.out.println("new version is : " + newconfig);
		if (greaterThan(newconfig, oldconfig)) {
			System.out.println("update required");
			newconfig = newbr.readLine();// this should be a download link
			System.out.println("download setup.exe from \"" + newconfig
					+ "\" to \"" + programdir + "\"");
			download(newconfig, programdir + "/setup.exe");

			String trunk = getParent(getParent(programdir));
			String en = trunk + "/disctree/en";
			// edit version in what's new
			editWhatsnew(en, programname, newversion);

			// edit version in program.html
			editHTML(en, programname, newversion);

		} else
			System.out.println("no update required");
	}

	private static void editHTML(String en, String programname, String version)
			throws IOException {
		// TODO Auto-generated method stub

		String desc = en + "/" + programname + ".html";
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		try {
			inputStream = getNewBufferedReader(desc);
			outputStream = new PrintWriter(new FileWriter(getParent(en) + "/"
					+ programname + ".html"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				if (l.indexOf("เวอร์ชัน") != -1) {// find line that contains
					// program version
					l = "<td>เวอร์ชัน:&nbsp;<span style=\"font-weight: bold;\">"
							+ version + "</span></td>";
				}
				outputStream.println(l);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
		File temp = new File(getParent(en) + "/" + programname + ".html");
		File dir = new File(getParent(desc));
		boolean success = temp.renameTo(new File(dir, temp.getName()));
		if (!success) {
			System.out.println("cannot move " + programname + ".html");
		}
		temp.delete();
	}

	private static void editWhatsnew(String en, String programname,
			String version) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;

		String whatsnew = en + "/whatsnews.html";
		try {
			inputStream = getNewBufferedReader(whatsnew);
			outputStream = new PrintWriter(new FileWriter(getParent(en)
					+ "/whatsnews.html"));

			String newprogramname = Character
					.toUpperCase(programname.charAt(0))
					+ programname.substring(1);
			String l;
			while ((l = inputStream.readLine()) != null) {
				if (l.indexOf(newprogramname) != -1) {// find line that contains
					// program version
					l = "<td>" + newprogramname + "</td><td>" + version
							+ "</td>";
				}
				outputStream.println(l);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
		File temp = new File(getParent(en) + "/whatsnews.html");
		File dir = new File(getParent(whatsnew));

		boolean success = temp.renameTo(new File(dir, temp.getName()));
		if (!success) {
			System.out.println("cannot move whatsnews.html");
		}
		temp.delete();

	}

	private static String getParent(String programdir) {
		// TODO Auto-generated method stub
		int l = programdir.length() - 1;
		while (programdir.charAt(l) != '/') {
			l--;
		}
		return programdir.substring(0, l);
	}

	private static void hasConfig(String newconfig, String programname,
			BufferedReader newbr) throws IOException {
		// TODO Auto-generated method stub
		while (!newconfig.equalsIgnoreCase(programname)) {
			// this will search for field of that program
			newconfig = newbr.readLine();
		}
		System.out.println("found config!!!");
	}

	private static BufferedReader getNewBufferedReader(String configdir)
			throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fstream = new FileInputStream(configdir);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		return br;
	}

	private static String getOldConfig(String programdir) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fstream = new FileInputStream(programdir
				+ "/config.txt");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String config = br.readLine();// this will be program name
		config = br.readLine();// this will be current version
		return config;
	}

	private static String getProgramname(String programdir) {
		// TODO Auto-generated method stub
		int l = programdir.length() - 1;
		while (programdir.charAt(l) != '/') {
			l--;
		}
		String programname = programdir.substring(l + 1, programdir.length());
		System.out.println("this program is " + programname);
		return programname;
	}

	private static boolean greaterThan(String newconfig, String oldconfig) {
		// TODO Auto-generated method stub
		int[] oldver = new int[5];
		int[] newver = new int[5];

		System.out.println("checking...");

		int o = 0;
		int n = 0;
		for (int i = 0; i < newconfig.length(); i++) {
			for (int j = 0; j < newconfig.length(); j++) {
				if (j == newconfig.length() - 1) {
					newver[n] = Integer.parseInt(newconfig.substring(i, j + 1));
					System.out.println(newver[n]);
					n++;
					i = j + 1;
				} else if (newconfig.charAt(j) == '.') {
					newver[n] = Integer.parseInt(newconfig.substring(i, j));
					System.out.print(newver[n]);
					n++;
					i = j + 1;
				}
			}
		}
		for (int i = 0; i < oldconfig.length(); i++) {
			for (int j = 0; j < oldconfig.length(); j++) {
				if (j == oldconfig.length() - 1) {
					oldver[o] = Integer.parseInt(oldconfig.substring(i, j + 1));
					System.out.println(oldver[o]);
					o++;
					i = j + 1;
				} else if (oldconfig.charAt(j) == '.') {
					oldver[o] = Integer.parseInt(oldconfig.substring(i, j));
					System.out.print(newver[o]);
					o++;
					i = j + 1;
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			if (newver[i] > oldver[i])
				return true;
		}
		return false;
	}

	public static void download(String httpServer, String destination)
			throws MalformedURLException, IOException {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			URL url = new URL(httpServer);
			URLConnection urlc = url.openConnection();

			bis = new BufferedInputStream(urlc.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(destination));

			int i;
			while ((i = bis.read()) != -1) {
				bos.write(i);
			}
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}

	}

	public static void createDescription(String config) {
		// TODO Auto-generated method stub
		String programlist = config + "/programlist.txt";
		try {
			FileInputStream fstream = new FileInputStream(programlist);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String desc;
			while ((desc = br.readLine()) != null) {
				new File(config + "/desc_file/" + desc + ".txt")
						.createNewFile();
			}
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void createHTML(String config, String projectFolder) throws IOException {
		// TODO Auto-generated method stub
		String template = config + "/template/programname.html";
		String en = projectFolder + "/svn/trunk/disctree/en";

		BufferedReader htmlstr = null;
		BufferedReader plist = null;
		BufferedReader desc = null;
		PrintWriter outStr = null;
		String p;
		plist = getNewBufferedReader(config + "/programlist.txt");
		while ((p = plist.readLine()) != null) {
			try {
				htmlstr = getNewBufferedReader(template);
				desc = getNewBufferedReader(config + "/desc_file/" + p + ".txt");
				outStr = new PrintWriter(new FileWriter(en + "/" + p + ".html"));

				String l;
				while ((l = htmlstr.readLine()) != null) {
					if (l.indexOf("programname") != -1) {
						l=l.replaceAll("programname", p);
					} else if (l.indexOf("description") != -1) {
						l = "<p>";
						outStr.println(l);
						while ((l = desc.readLine()) != null) {
							outStr.println(l);
						}
						l = "</p>";
						outStr.println(l);
					}
					outStr.println(l);
				}
			} finally {
				if (htmlstr != null) {
					htmlstr.close();
				}
				if (outStr != null) {
					outStr.close();
				}
			}
		}
	

	}
}