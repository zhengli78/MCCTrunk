import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.LongSumReducer;
import org.apache.hadoop.mapred.lib.TokenCountMapper;
import java.io.IOException;
import java.util.*;

public class WordCount {
		public static void main(String[] args) {
		JobClient client = new JobClient();
		JobConf conf = new JobConf(WordCount.class);
		
		FileInputFormat.addInputPath(conf, new Path(args[0]));/// The dir here is the Hadoop directory
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(LongWritable.class);
		
		conf.setMapperClass(TokenCountMapper.class);
		conf.setCombinerClass(LongSumReducer.class); 
		conf.setReducerClass(LongSumReducer.class); 
		client.setConf(conf);
		try {
		JobClient.runJob(conf);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
		
  public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
          int sum = 0;
          while (values.hasNext()) {
            sum += values.next().get();
          }
          output.collect(key, new IntWritable(sum));
        }
      }
  public class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
      private final IntWritable one = new IntWritable(1);
      Text word = new Text();
      public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
	        String line = value.toString();
	        if(!line.isEmpty()){
		        String[] words = line.split(" ");
		        for(int i=0;i<words.length;i++)
		        {
		        	word.set(words[i]);
		        	output.collect(word, one);
		        }
	        }
      	}
  	}
}


    