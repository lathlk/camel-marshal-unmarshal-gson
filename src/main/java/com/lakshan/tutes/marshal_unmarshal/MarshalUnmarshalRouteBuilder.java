package com.lakshan.tutes.marshal_unmarshal;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;
import org.apache.camel.model.DataFormatDefinition;

import com.google.gson.FieldNamingPolicy;
import com.lakshan.tutes.marshal_unmarshal.entity.Dog;
import com.lakshan.tutes.marshal_unmarshal.entity.response.OwnerDetails;

public class MarshalUnmarshalRouteBuilder extends RouteBuilder{
	@Override
	public void configure() throws Exception {
		
		DataFormatDefinition df = new DataFormatDefinition();
		GsonDataFormat gdfd = new GsonDataFormat();
		gdfd.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);		
		gdfd.setUnmarshalType(Dog.class);
		df.setDataFormat(gdfd);
		
		DataFormatDefinition df2 = new DataFormatDefinition();
		GsonDataFormat gdfd2 = new GsonDataFormat();
		gdfd2.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);		
		gdfd2.setUnmarshalType(OwnerDetails.class);
		df2.setDataFormat(gdfd);
		
		restConfiguration()
			.component("jetty")
			.scheme("http")
			.host("0.0.0.0")
			.port(30000);
		
		rest("/tutes/")
			.post("unmarshal-marshal")
				.to("direct:unmarshal-marshal");
		
		from("direct:unmarshal-marshal")
			.unmarshal(df)
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					Dog dog = (Dog) exchange.getIn().getBody();
					OwnerDetails ownerDetails = new OwnerDetails();
					ownerDetails.setId("001");
					ownerDetails.setName("Lakshan");
					ownerDetails.setAddress("Test Address");
					ownerDetails.setDogName(dog.getName());
					ownerDetails.setDogCategory(dog.getCategory().getName());
					exchange.getIn().setBody(ownerDetails);
				}
			})
			.marshal(df2)
		.routeId("unmarshal-route");
	}
}
