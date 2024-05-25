package distributedlogger;

import java.util.List;
import java.util.Random;

public class DistributedLogStrategy implements LogStrategy {
    private List<LogAggregator> aggregators;

    public DistributedLogStrategy(List<LogAggregator> aggregators) {
        this.aggregators = aggregators;
    }

    @Override
    public void log(LogEvent event) {
        LogAggregator aggregator = selectAggregator();
        aggregator.aggregate(event);
    }

    private LogAggregator selectAggregator() {
        return aggregators.get(new Random().nextInt(aggregators.size()));
    }

    public List<LogAggregator> getAggregators() {
        return aggregators;
    }
}
