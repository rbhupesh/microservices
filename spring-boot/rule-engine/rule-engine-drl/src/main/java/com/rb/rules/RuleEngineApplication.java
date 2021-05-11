package com.rb.rules;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.rb.rules.service.RuleService;
import com.rb.rules.service.impl.RuleServiceImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(title = "Rule service API",
					version = "1.0",
					description = "Rule service provide api interface for drool engine"),
		servers = @Server(url = "https://www.rb-rules.com",
					description = "spring-boot internal tomcat server is in use")
		)
@SpringBootApplication
public class RuleEngineApplication {

	/*
	 * localhost url
	 * http://localhost:8080/v3/api-docs/
	 */
	public static void main(String[] args) {
		SpringApplication.run(RuleEngineApplication.class, args);
	}
	
	private KieServices kieServices = KieServices.Factory.get();
	
	@Bean
	public RuleService getRuleServiceImpl(){
		return new RuleServiceImpl();
	}

	@Bean
	@Qualifier("kieSession2")
	public StatelessKieSession getKieSession2() throws IOException {
		System.out.println("\n### kieSession2 created...");
		return getKieContainer2().newStatelessKieSession();
	}

	@Bean
	@Qualifier("KieContainer1")
	public KieContainer getKieContainer1() throws IOException {
		System.out.println("\n### KieContainer created...");
		getKieRepository();
		KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem(1));
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		return kContainer;

	}
	
	@Bean
	@Qualifier("KieContainer2")
	public KieContainer getKieContainer2() throws IOException {
		System.out.println("\n### KieContainer created...");
		getKieRepository();
		KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem(2));
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		return kContainer;

	}
	
	private KieFileSystem getKieFileSystem(int fileId) throws IOException {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource("rules"+fileId+".drl"));
		return kieFileSystem;

	}
	
	private void getKieRepository() {
		final KieRepository kieRepository = kieServices.getRepository();
		kieRepository.addKieModule(new KieModule() {
			public ReleaseId getReleaseId() {
				return kieRepository.getDefaultReleaseId();
			}
		});
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Rule-Engine Service Started");
		};
	}

}
