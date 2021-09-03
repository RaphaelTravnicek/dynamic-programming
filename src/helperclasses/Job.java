package helperclasses;

import exceptions.InvalidArgumentException;

public class Job implements Comparable<Job> {
    private String name;
    private final int startTime;
    private final int endTime;
    private final int weight;

    /**
     * Instantiate a Job object with start time, end time and weight.
     * @param startTime the start time of the job
     * @param endTime the end time of the job
     * @param weight the weight/ value of the job
     * @throws InvalidArgumentException if start time is negative, or end time is smaller than or equal to start time, or weight is negative
     */
    public Job(int startTime, int endTime, int weight) throws InvalidArgumentException {
        if (startTime < 0) {
            throw new InvalidArgumentException("Exception: Given start time must be non-negative.");
        }
        if (endTime <= startTime) {
            throw new InvalidArgumentException("Exception: Given end time must be greater than given start time.");
        }
        if (weight < 0) {
            throw new InvalidArgumentException("Exception: Given value must be non-negative.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.weight = weight;

    }
    /**
     * Instantiate a Job object with start time, end time and weight.
     * @param startTime the start time of the job
     * @param endTime the end time of the job
     * @param weight the weight/ value of the job
     * @throws InvalidArgumentException if name is null, or start time is negative, or end time is smaller than or equal to start time, or weight is negative
     */
    public Job(String name, int startTime, int endTime, int weight) throws InvalidArgumentException {
        if (name == null) {
            throw new InvalidArgumentException("Exception: Given name must not be null.");
        }
        if (startTime < 0) {
            throw new InvalidArgumentException("Exception: Given start time must be non-negative.");
        }
        if (endTime <= startTime) {
            throw new InvalidArgumentException("Exception: Given end time must be greater than given start time.");
        }
        if (weight < 0) {
            throw new InvalidArgumentException("Exception: Given value must be non-negative.");
        }
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Job j = (Job) o;
        return this.name.equals(j.name) && this.startTime == j.startTime && this.endTime == j.endTime;
    }

    @Override
    public int compareTo(Job o) {
        if (o.endTime < this.endTime) {
            return 1;
        } else if (o.endTime > this.endTime) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Job: " + this.name + " (Start Time: " + this.startTime + ", End Time: " + this.endTime + ", Weight: " + this.weight + ")";
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getWeight() {
        return weight;
    }
}
