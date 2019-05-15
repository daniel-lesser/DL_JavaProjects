package problem14;

public class Doctor implements Runnable {

	int consultationTime;
	int consultCount;

	public Doctor(int consultationTime) {
		this.consultationTime = consultationTime;
	}

	@Override
	public void run() {
		while (consultCount < ClinicManager.maxPatientCount) {
			Patient p = null;
			synchronized (Clinic.patientQ) {
				if (Clinic.patientQ.size() > 0) {
					p = Clinic.patientQ.poll();
				}
			}
			if (p != null) {
				try {

					consultCount++;
					System.out.println("Consulting patient #" + p.id);
					Thread.sleep(consultationTime);
					p.endTime = System.currentTimeMillis();
					ClinicManager.patientWaitTime += (p.endTime - p.startTime);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

}

//working
/*
 * public void run() { while (consultCount < ClinicManager.maxPatientCount) {
 * 
 * 
 * try { synchronized (Clinic.patientQ) { if (Clinic.patientQ.size() > 0) {
 * Patient p = Clinic.patientQ.poll(); consultCount++;
 * System.out.println("Consulting patient #" + p.id);
 * Thread.sleep(consultationTime); p.endTime = System.currentTimeMillis();
 * ClinicManager.patientWaitTime += (p.endTime - p.startTime); }
 * 
 * } } catch (InterruptedException e) { e.printStackTrace(); }
 * 
 * } }
 * 
 * }
 */

/*
 * while (consultCount < ClinicManager.maxPatientCount) { try { if
 * (Clinic.patientQ.size() > 0) { Patient p; synchronized (Clinic.patientQ) { p
 * = Clinic.patientQ.poll(); } if (p != null) { consultCount++;
 * System.out.println("Consulting patient #" + p.id);
 * Thread.sleep(consultationTime); p.endTime = System.currentTimeMillis();
 * ClinicManager.patientWaitTime += (p.endTime - p.startTime); }
 * 
 * } } catch (InterruptedException e) { e.printStackTrace(); }
 */