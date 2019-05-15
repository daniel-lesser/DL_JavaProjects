package problem14;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Clinic implements Runnable {

	static Queue<Patient> patientQ = new LinkedList<>();
	int patientCount;
	long clinicOpenTime;
	int maxPatientArrivalGap;

	public Clinic(int maxPatientArrivalGap) {
		this.maxPatientArrivalGap = maxPatientArrivalGap;
	}

	@Override
	public void run() {
		clinicOpenTime = System.currentTimeMillis();
		while (patientCount < ClinicManager.maxPatientCount) {
			try {
				synchronized (patientQ) {
					patientQ.offer(new Patient());
				}
				patientCount++;
				Random rand = new Random();
				int r = rand.nextInt(maxPatientArrivalGap);
				Thread.sleep(r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
