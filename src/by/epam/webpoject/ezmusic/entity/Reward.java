package by.epam.webpoject.ezmusic.entity;

/**
 * Created by Anton Shulha on 11.12.2016.
 */
public class Reward {
    private Long rewardId;
    private String name;
    private int year;

    public Reward(){

    }
    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reward)) return false;

        Reward reward = (Reward) o;

        if (year != reward.year) return false;
        if (!rewardId.equals(reward.rewardId)) return false;
        return name.equals(reward.name);

    }

    @Override
    public int hashCode() {
        int result = rewardId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + year;
        return result;
    }
}
