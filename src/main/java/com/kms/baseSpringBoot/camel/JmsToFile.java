package com.kms.baseSpringBoot.camel;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import static java.util.Collections.singletonList;

/**
 * An example class for demonstrating some basics behind Camel. This
 * example sends some text messages on to a JMS Queue, consumes them and
 * persists them to disk
 */
public final class JmsToFile {

    private JmsToFile() {
    }

    public static void main(String[] args) throws Exception {
        // tag::e1[]
        try (CamelContext context = new DefaultCamelContext()) {
            // end::e1[]
            // Set up the ActiveMQ JMS Components
            // tag::e2[]
            ActiveMQConnectionFactory connectionFactory = createActiveMQConnectionFactory();
            // Note we can explicitly name the component
            context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
            // end::e2[]
            // Add some configuration by hand ...
            // tag::e3[]
            context.addRoutes(new MyRouteBuilder());
            // end::e3[]
            // Now everything is set up - lets start the context
            context.start();
            // Camel template - a handy class for kicking off exchanges
            // tag::e4[]
            try (ProducerTemplate template = context.createProducerTemplate()) {
                // end::e4[]
                // Now send some test text to a component - for this case a JMS Queue
                // The text get converted to JMS messages - and sent to the Queue
                // test.queue
                // The file component is listening for messages from the Queue
                // test.queue, consumes
                // them and stores them to disk. The content of each file will be the
                // test we sent here.
                // The listener on the file component gets notified when new files are
                // found ... that's it!
                // tag::e5[]
                for (int i = 0; i < 10; i++) {
                    template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
                }
            }
            // end::e5[]

            // wait a bit and then stop
            Thread.sleep(1000);
        }
    }

    static ActiveMQConnectionFactory createActiveMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        connectionFactory.setTrustAllPackages(false);
        connectionFactory.setTrustedPackages(singletonList("org.apache.camel.example.jmstofile"));
        return connectionFactory;
    }

    static class MyRouteBuilder extends RouteBuilder {

        @Override
        public void configure() {
            from("test-jms:queue:test.queue").to("file:target/messages");
        }
    }
}