package mazeTests;

import java.io.*;

import maze.MockQuestion;

public class MockQuestionSerializationTest {

	public static void main(String[] args) throws Exception {
		MockQuestion mq = new MockQuestion();
		FileOutputStream fos = new FileOutputStream(mq.getID() + "");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(mq);
		
		FileInputStream fis = new FileInputStream(mq.getID() + "");
		ObjectInputStream ois = new ObjectInputStream(fis);
		MockQuestion q = (MockQuestion)ois.readObject();
		
		System.out.println(q.display());
	}

}
