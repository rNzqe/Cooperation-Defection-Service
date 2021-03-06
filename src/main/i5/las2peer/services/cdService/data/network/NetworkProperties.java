package i5.las2peer.services.cdService.data.network;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import i5.las2peer.services.cdService.data.util.table.TableRow;

@Embeddable
public class NetworkProperties extends Properties {

	///// Entity Fields /////

	@Basic
	private int nodes;

	@Basic
	private int edges;

	@Basic
	private double density;

	@Basic
	private double averageDegree;

	@Basic
	private double clusteringCoefficient;
	
	@Basic
	private double degreeDeviation;

	///// Getter /////

	@JsonProperty
	public int getNodes() {
		if (this.nodes == 0)
			return -1;
		return nodes;
	}

	@JsonProperty
	public int getEdges() {
		return edges;
	}

	@JsonProperty
	public double getDensity() {
		if (this.density == 0.0)
			this.density = calculateDensity(getNodes(), getEdges());
		return density;
	}

	@JsonProperty
	public double getAverageDegree() {
		if (this.averageDegree == 0.0)
			this.averageDegree = calculateAverageDegree(getNodes(), getEdges());
		return averageDegree;
	}
	
	@JsonProperty
	public double getDegreeDeviation() {
		return degreeDeviation;
	}

	@JsonProperty
	public double getClusteringCoefficient() {
		return clusteringCoefficient;
	}

	///// Setter /////

	public void setNodes(int nodes) {
		this.nodes = nodes;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public void setAverageDegree(double averageDegree) {
		this.averageDegree = averageDegree;
	}

	public void setClusteringCoefficient(double clusteringCoefficient) {
		this.clusteringCoefficient = clusteringCoefficient;
	}
	
	public void setDegreeDeviation(double degreeDeviation) {
		this.degreeDeviation = degreeDeviation;
	}

	///// Methods /////

	@Override
	@JsonIgnore
	public double getProperty(PropertyType property) {

		switch (property) {
		case SIZE:
			return getNodes();

		case DENSITY:
			return getDensity();

		case AVERAGE_DEGREE:
			return getAverageDegree();
			
		case DEGREE_DEVIATION:
			return getDegreeDeviation();

		case CLUSTERING_COEFFICIENT:
			return getClusteringCoefficient();

		default:
			break;
		}
		return 0.0;
	}

	//////// Calculations ///////

	public double calculateDensity(int n, int e) {

		if (n < 2)
			return 0.0;

		int num = 2 * e;
		int den = n * (n - 1);

		return (num / (double) den);
	}

	public double calculateAverageDegree(int n, int e) {

		if (n == 0.0)
			return 0.0;

		return ((2.0 * e) / (n));
	}
	
	public double calculateDegreeDeviation(double[] values) {

		if (values == null)
			return 0.0;

		double deviation = super.calculateDeviation(values);
		if (Double.isNaN(deviation)) 
			return 0.0;
		return deviation;
	}


	/////////// Print ///////////

	public TableRow toTableLine() {

		TableRow line = new TableRow();
		line.add(getDensity()).add(getAverageDegree()).add(getDegreeDeviation());
		return line;
	}

	public TableRow toHeadLine() {

		TableRow line = new TableRow();
		line.add("Density").add("Average Degree").add("Degree Deviation");
		return line;

	}


}
