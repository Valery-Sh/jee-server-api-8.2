package org.netbeans.modules.jeeserver.base.embedded;

public final class Statistics {
    /** Creates a new instance of Statistics */
    
    public static final Statistics.TimeConsumer FLUSH =
            new Statistics.TimeConsumer("Flush");//NOI18N
    public static final Statistics.TimeConsumer LOAD =
            new Statistics.TimeConsumer("Load");//NOI18N
    public static final Statistics.TimeConsumer REMOVE_NODE =
            new Statistics.TimeConsumer("Remove node");//NOI18N
    public static final Statistics.TimeConsumer CHILDREN_NAMES =
            new Statistics.TimeConsumer("Children names");//NOI18N
    
    
    private Statistics() {}
    
    
    public static Statistics.StopWatch getStopWatch(Statistics.TimeConsumer consumer) {
        return new Statistics.StopWatch(consumer);
    }

    public static Statistics.StopWatch getStopWatch(Statistics.TimeConsumer consumer, boolean start) {
        Statistics.StopWatch retval = new Statistics.StopWatch(consumer);
        if (start) {
            retval.start();
        }
        return retval;
    }
    
    public static final class TimeConsumer {
        private int elapsedTime;
        private int numberOfCalls;
        private final String description;
        
        private TimeConsumer(final String description) {
            this.description = description;
        }
        
        public int getConsumedTime() {
            return elapsedTime;
        }
        
        public int getNumberOfCalls() {
            return numberOfCalls;
        }        
        
        public void reset() {
    	    elapsedTime = 0;
            numberOfCalls = 0;
        }

        public @Override String toString() {
            return description + ": " + numberOfCalls + " calls in " + elapsedTime + "ms";//NOI18N
        }
		
        private void incrementNumerOfCalls() {
            numberOfCalls++;
            
        }
    }
    
    public static final class StopWatch {
        private long startTime = 0;
        private final Statistics.TimeConsumer activity;
        
        
        /** Creates a new instance of ElapsedTime */
        private StopWatch(Statistics.TimeConsumer activity) {
            this.activity = activity;
        }
        
        
        public void start() {
            startTime = System.currentTimeMillis();
        }
        
        public void stop() {
            assert startTime != 0;
            activity.elapsedTime += (System.currentTimeMillis() - startTime);
            activity.incrementNumerOfCalls();
            startTime = 0;
        }
    }
}

