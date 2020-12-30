package hr.ericsson.etkc.ebusiness;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

@Path("/opencv")
public class OpenCVResource {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);		
	}
	
	CascadeClassifier cascadeClassifier = new CascadeClassifier("C:\\dev\\projects\\opencv-back\\src\\main\\resources\\harscade\\haarcascade_frontalface_alt.xml");
	MatOfRect facesDetected = new MatOfRect();
	
	public OpenCVResource() {			
		//cascadeClassifier.load("C:\\dev\\projects\\opencv-back\\src\\main\\resources\\harscade\\haarcascade_frontalface_alt.xml");
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Rect[] upload(@MultipartForm MultipartBody data) throws IOException {
		System.out.println("jel ovo radi!?");
		System.out.println(data.fileName);
		System.out.println(data.file);
		//return detectFace(getMat(data.file));
		return null;
	}

	@GET
	@Path("/test")
	public String test() {
		System.out.println(System.getProperty("java.library.path"));
		System.out.println("test");
		return System.getProperty("java.library.path");
	}

//	private Rect[] detectFace(Mat mat) {
//		int minFaceSize = Math.round(mat.rows() * 0.1f);
//		cascadeClassifier.detectMultiScale(mat, facesDetected, 1.1, 3, Objdetect.CASCADE_SCALE_IMAGE, new Size(minFaceSize, minFaceSize), new Size());
//		Rect[] facesArray = facesDetected.toArray();
//		return facesArray;
//	}
	
	private Mat getMat(InputStream is) throws IOException {
		BufferedImage image = ImageIO.read(is);
		int rows = image.getWidth();
		int cols = image.getHeight();
		int type = CvType.CV_16UC1;
		Mat newMat = new Mat(rows, cols, type);

		for (int r = 0; r < rows; r++) {
		    for (int c = 0; c < cols; c++) {
		        newMat.put(r, c, image.getRGB(r, c));
		    }
		}		
		return newMat;		
	}
	

}
