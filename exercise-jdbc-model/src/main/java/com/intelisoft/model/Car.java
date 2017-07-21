package com.intelisoft.model;

public final class Car extends Model {

	private String maker;

	private String model;

	private int productionYear;

	private String color;

	private String engineType;

	private int odometer;

	private Consumer consumer;

	public Car() {
	}

	public Car(String maker, String model, int productionYear, String color, String engineType, int odometer,
			Consumer consumer) {
		this.maker = maker;
		this.model = model;
		this.productionYear = productionYear;
		this.color = color;
		this.engineType = engineType;
		this.odometer = odometer;
		this.consumer = consumer;
	}

	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getProductionYear() {
		return productionYear;
	}
	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public int getOdometer() {
		return odometer;
	}
	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", maker=");
		builder.append(maker);
		builder.append(", model=");
		builder.append(model);
		builder.append(", productionYear=");
		builder.append(productionYear);
		builder.append(", color=");
		builder.append(color);
		builder.append(", engineType=");
		builder.append(engineType);
		builder.append(", odometer=");
		builder.append(odometer);
		builder.append(", consumer=");
		builder.append(consumer);
		builder.append("]");
		return builder.toString();
	}
}
