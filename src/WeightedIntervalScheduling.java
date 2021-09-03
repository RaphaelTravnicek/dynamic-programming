import helperclasses.*;
import exceptions.InvalidArgumentException;

import java.util.Arrays;

public class WeightedIntervalScheduling {
    private final Job[] jobs;
    private final int[] compatibleJob;
    private final int[] M;

    /**
     * Instantiate an instance of the Weighted Interval Scheduling Problem.
     * @param jobs an array representing the jobs
     * @throws InvalidArgumentException if one of the given jobs is null
     */
    public WeightedIntervalScheduling(Job[] jobs) throws InvalidArgumentException {
        if (isValidInput(jobs)) {
            this.jobs = this.extendJobs(jobs);
            this.compatibleJob = this.compatibleJob(this.jobs);
            this.M = this.weightedIntervalScheduling(this.jobs);
        } else {
            throw new InvalidArgumentException("Exception: Given jobs must not be null");
        }
    }

    /**
     * Solve the Weighted Interval Scheduling Problem instance.
     * @return a String representation of the solution
     */
    public String solve() {
        return "Solution: " + this.solveRecursively(this.jobs.length - 1);
    }

    private String solveRecursively(int i) {
        if (i == 0) {
            return "";
        } else if (this.jobs[i].getWeight() + this.M[this.compatibleJob[i]] > this.M[i - 1]) {
            return this.jobs[i] + " " + this.solveRecursively(this.compatibleJob[i]);
        } else {
            return solveRecursively(i - 1);
        }
    }

    private int[] weightedIntervalScheduling(Job[] jobs) {
        int[] M = new int[jobs.length];
        M[0] = 0;
        for (int i = 1; i < jobs.length; i++) {
            M[i] = Math.max(jobs[i].getWeight() + M[this.compatibleJob[i]], M[i - 1]);
        }
        return M;
    }

    private int[] compatibleJob(Job[] jobs) {
        int[] compatibleJobs = new int[jobs.length];
        int index = jobs.length - 1;
        for (int i = jobs.length - 1; i >= 0; i--) {
            compatibleJobs[index--] = findCompatibleJob(jobs, i);
        }
        return compatibleJobs;
    }

    private static int findCompatibleJob(Job[] jobs, int job) { // TODO binary search
        for (int i = job - 1; i >= 1; i--) {
            if (jobs[i].getEndTime() <= jobs[job].getStartTime()) {
                return i;
            }
        }
        return 0;
    }

    private Job[] extendJobs(Job[] jobs) {
        Arrays.sort(jobs);
        Job[] newJobs = new Job[jobs.length + 1];
        for (int i = 1; i < newJobs.length; i++) {
            newJobs[i] = jobs[i - 1];
        }
        return newJobs;
    }

    private boolean isValidInput(Job[] jobs) {
        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i] == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Weighted Interval Scheduling Problem").append("\n").append("Jobs: \n");
        for (int i = 1; i < this.jobs.length; i++) {
            s.append(this.jobs[i]);
            if (i != this.jobs.length - 1) {
                s.append(",\n");
            }
        }
        return s.toString();
    }
}
