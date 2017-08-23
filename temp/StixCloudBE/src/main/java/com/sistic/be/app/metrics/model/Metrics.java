package com.sistic.be.app.metrics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "processors",
		"instance.uptime",
		"uptime",
		"systemload.average",
		"heap.committed",
		"heap.init",
		"heap.used",
		"heap",
		"nonheap.committed",
		"nonheap.init",
		"nonheap.used",
		"nonheap",
		"threads.peak",
		"threads.daemon",
		"threads.totalStarted",
		"threads",
		"classes",
		"classes.loaded",
		"classes.unloaded",
		"gc.ps_scavenge.count",
		"gc.ps_scavenge.time",
		"gc.ps_marksweep.count",
		"gauge.response.metrics",
		"gc.ps_marksweep.time",
		"counter.status.200.metrics",
		"httpsessions.max",
		"httpsessions.active"})

@Deprecated
public class Metrics {
	private long memoryFree;
	private long memoryAssigned;
	
	/**
	 * @return the memoryFree
	 */
	
	public long getMemoryFree() {
		return memoryFree;
	}

	/**
	 * @param memoryFree the memoryFree to set
	 */
	@JsonProperty("mem.free")
	public void setMemoryFree(long memoryFree) {
		this.memoryFree = memoryFree;
	}

	/**
	 * @return the memoryAssigned
	 */
	public long getMemoryAssigned() {
		return memoryAssigned;
	}

	/**
	 * @param memoryAssigned the memoryAssigned to set
	 */
	@JsonProperty("mem")
	public void setMemoryAssigned(long memoryAssigned) {
		this.memoryAssigned = memoryAssigned;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Metrics [memoryFree=" + memoryFree + ", memoryAssigned=" + memoryAssigned + "]";
	}

}


/**
{"mem":398378,
"mem.free":305940,
"processors":4,"instance.uptime":5501,"uptime":453131,"systemload.average":-1.0,"heap.committed":336384,"heap.init":126976,"heap.used":30443,"heap":1804288,"nonheap.committed":63424,"nonheap.init":2496,"nonheap.used":61992,"nonheap":0,"threads.peak":38,"threads.daemon":23,"threads.totalStarted":103,"threads":27,"classes":6900,"classes.loaded":6902,"classes.unloaded":2,"gc.ps_scavenge.count":13,"gc.ps_scavenge.time":296,"gc.ps_marksweep.count":4,"gc.ps_marksweep.time":1109,"httpsessions.max":-1,"httpsessions.active":0}
**/