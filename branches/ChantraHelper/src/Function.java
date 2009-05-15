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
import java.util.Calendar;

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

	private static void editHTML(String en, String programname, String version,
			String config) throws IOException {
		// TODO Auto-generated method stub

		String desc = en + "/" + programname + ".html";
		BufferedReader inputStream = null;
		BufferedReader desctxt = null;
		PrintWriter outputStream = null;
		try {
			inputStream = getNewBufferedReader(desc);
			desctxt = getNewBufferedReader(config + "/desc_file/" + programname
					+ ".txt");
			outputStream = new PrintWriter(new FileWriter(getParent(en) + "/"
					+ programname + ".html"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				if (l.indexOf("<p>") != -1) {
					l = "<p>";
					outputStream.print(l);
					while ((l = desctxt.readLine()) != null) {
						outputStream.print(l);
					}
					l = "</p>";
				} else if (l.indexOf("เวอร์ชัน") != -1) {
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

	public static void update2(String projectFolder, String configdir)
			throws IOException {
		BufferedReader configfile;

		configfile = getNewBufferedReader(configdir + "/config.txt");
		String en = projectFolder + "/svn/trunk/disctree/en";
		String programname;
		String version;
		String link;
		String programfolder;
		while ((programname = configfile.readLine()) != null) {
			System.out.print("\tprogramname:"+programname);
			version = configfile.readLine();
			System.out.print("\tversion:"+version);
			link = configfile.readLine();
			System.out.println("\tlink:"+link);
			editHTML(en, programname, version, configdir);
			programfolder = projectFolder + "/svn/trunk/programs/"
					+ programname;
			//download(link, programfolder + "setup.exe");
			programname = configfile.readLine();
		}

	}

	private static String getParent(String programdir) {
		// TODO Auto-generated method stub
		int l = programdir.length() - 1;
		while (programdir.charAt(l) != '/') {
			l--;
		}
		return programdir.substring(0, l);
	}

	private static BufferedReader getNewBufferedReader(String configdir)
			throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fstream = new FileInputStream(configdir);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		return br;
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
			BufferedReader br = getNewBufferedReader(programlist);

			String desc;
			if (!new File(config + "/desc_file").exists()) {
				new File(config + "/desc_file").mkdir();
			}
			while ((desc = br.readLine()) != null) {
				new File(config + "/desc_file/" + desc + ".txt")
						.createNewFile();
			}
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void createWhatsnew(String config, String projectFolder)
			throws IOException {
		String template = config + "/template/whatsnews.html";
		String en = projectFolder + "/svn/trunk/disctree/en";

		BufferedReader whatsnewstr = null;
		BufferedReader plist = null;
		PrintWriter outwhatsnew = null;

		String p;
		plist = getNewBufferedReader(config + "/config.txt");
		outwhatsnew = new PrintWriter(new FileWriter(en + "/whatsnew.html"));

		try {
			whatsnewstr = getNewBufferedReader(template);
			String programname;
			String version;
			String l;
			while ((l = whatsnewstr.readLine()) != null) {
				if (l.indexOf("<tr bgcolor=\"#ffffff\">") != -1) {
					while ((p = plist.readLine()) != null) {
						programname = p;
						version = plist.readLine();
						outwhatsnew.println("<tr bgcolor=\"#ffffff\">");
						outwhatsnew.println("<td>" + programname + "</td><td>"+version+"</td>");
						outwhatsnew.println("</tr>");
						p=plist.readLine();
						p=plist.readLine();
					}
				}
				outwhatsnew.println(l);
			}
		} finally {
			if (whatsnewstr != null) {
				whatsnewstr.close();
			}
			if (outwhatsnew != null) {
				outwhatsnew.close();
			}
		}

	}

	public static void createlch(String config, String projectFolder)
			throws IOException {

		String en = projectFolder + "/svn/trunk/disctree/en";

		BufferedReader plist = null;
		PrintWriter outlch = null;

		String p;
		plist = getNewBufferedReader(config + "/programlist.txt");
		while ((p = plist.readLine()) != null) {
			try {

				outlch = new PrintWriter(new FileWriter(en + "/" + p
						+ "_install.lch"));

				outlch.println("[Launch]");
				outlch.println("ExecuteFile=${cwd}\\..\\programs\\" + p
						+ "\\setup.exe");

			} finally {
				if (outlch != null) {
					outlch.close();
				}
			}
		}
	}

	public static void createHTML(String config, String projectFolder)
			throws IOException {
		// TODO Auto-generated method stub
		String template = config + "/template/programname.html";
		String en = projectFolder + "/svn/trunk/disctree/en";

		BufferedReader htmlstr = null;
		BufferedReader plist = null;
		PrintWriter outHtml = null;

		String p;
		plist = getNewBufferedReader(config + "/programlist.txt");
		while ((p = plist.readLine()) != null) {
			try {
				htmlstr = getNewBufferedReader(template);
				outHtml = new PrintWriter(
						new FileWriter(en + "/" + p + ".html"));

				String l;
				while ((l = htmlstr.readLine()) != null) {
					if (l.indexOf("programname") != -1) {
						l = l.replaceAll("programname", p);
					} else if (l.indexOf("versionnumber") != -1) {
						l = l.replaceFirst("versionnumber", "0.0");
					} else if (l.indexOf("year") != -1) {
						l = l.replaceFirst("year", Integer.toString(Calendar
								.getInstance().get(Calendar.YEAR)));
					}
					outHtml.println(l);
				}

			} finally {
				if (htmlstr != null) {
					htmlstr.close();
				}
				if (outHtml != null) {
					outHtml.close();
				}
			}
		}

	}
}