//package evaluate_streets;

/*-
 * 
  * @author PEAK_
 */
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

class Timer implements Constants {
	private static int p;
	/*- For elapsed time measurement. */
	long elapsed;
	long startTime;
	long startTimeMillis;
	long e; // Elapsed time in nano sconds
	long e1; // Nano
	long e2; // Mili
	long e3; // Seconds

	/*- Constructor. */
	Timer() {
		elapsed = startTime = 0;
	}

	/*-  Start elapsed time. */
	void startTimer() {
		startTime = System.nanoTime();
		startTimeMillis = System.currentTimeMillis();
	}

	/*-   Reset elapsed time. */
	void resetTimer() {
		elapsed = startTime = 0;
	}

	/*-   Stop elapsed time. */
	void stopTimer() {
		elapsed = startTime = 0;
	}

	/*-  Sum timers. */
	void sumTimer() {
		elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		startTime = 0L;
	}

	/*-  Show elapsed time  . */
	void showTimer(String s) {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		if (elapsed == 0) {
			return;
		}
		e = elapsed;
		e1 = e / 1000L;
		e2 = e / 1000000L;
		e3 = e / 1000000000L;
		Logger.log(
				new StringBuilder().append("Elapsed time for ").append(s).append(" ").append(e).append(" Nano seconds ")
						.append(e1).append(" Mili seconds ").append(e2).append(" seconds ").append(e3).toString());
	}

	/*-  Get time in seconds. */
	double getTimerSeconds() {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		e = elapsed;
		return e2 = e / 10000000000L;
	}

	/**
	 * elapsed time in hours/minutes/seconds
	 * 
	 * @return String
	 */
	static String getElapsedTime(long milliseconds) {
		final var format = String.format("%%0%dd", 2);
		return new StringBuilder().append(String.format(format, (milliseconds / 1000) / 3600)).append(":")
				.append(String.format(format, ((milliseconds / 1000) % 3600) / 60)).append(":")
				.append(String.format(format, (milliseconds / 1000) % 60)).toString();
	}

	/*-  
	 * Get elapsed time in seconds. 
	 * No conditions, just get time since startTimer()
	 */
	long getElapsedTimeMilliLong() {
		return (System.currentTimeMillis() - startTimeMillis);
	}

	/*-  
	 * Get elapsed time in seconds. 
	 * No conditions, just get time since startTimer()
	 */
	String getElapsedTimeString() {
		return getElapsedTime(System.currentTimeMillis() - startTimeMillis);
	}

	/*-  Get time in seconds as a String. */
	String getTimerSecondsString() {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		e = elapsed;
		e2 = e / 10000000000L;
		return decFormat(e2);
	}

	/*- - Get time in mili seconds. */
	double getTimerMili() {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		e = elapsed;
		return e1 = e / 1000L;
	}

	/*-   Get time in mili seconds. */
	long getTimerMiliLong() {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		e = elapsed;
		e1 = e / 1000L;
		if (p >= 80) {
			return e1;
		}
		++p;
		Logger.log("//getTimer " + e1);
		return e1;
	}

	/*-  Get time in mili seconds as a String. */
	String getTimerMiliString() {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		e = elapsed;
		e1 = e / 1000L;
		return decFormat(e1);
	}

	/*-  Get time in Micro seconds. */
	double getTimerMicro() {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		return e = elapsed;
	}

	/*-  Get time in Micro seconds. */
	long getTimerMicroLong() {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		return e = elapsed;
	}

	/*-  Get time in Micro seconds. */
	double getAverageTimerMicro(int num) {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		return 1. * num * elapsed / 1.;
	}

	/*-  Get time in Mili seconds. */
	double getAverageTimerMili(int num) {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime);
		}
		final double e = 1. * num * elapsed / 1.;
		resetTimer();
		return e;
	}

	/*-  Get time in Mili seconds. */
	double getAverageTimerSeconds(int num) {
		if (elapsed == 0) {
			elapsed += TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
		}
		return 1. * num * elapsed / 1.;
	}


	/*-  Convert long to decimal format. */
	private String decFormat(long number) {
		return (new DecimalFormat("###,###,###,###.###")).format(number);
	}

}
