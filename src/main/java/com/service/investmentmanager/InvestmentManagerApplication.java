package com.service.investmentmanager;

import com.service.investmentmanager.dao.AccountRepository;
import com.service.investmentmanager.dao.CustomerRepository;
import com.service.investmentmanager.dao.PortfolioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class InvestmentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CustomerRepository customerRepository, PortfolioRepository portfolioRepository, AccountRepository accountRepository) {
		return args -> {

			/*CustomerData customerData = CustomerData.builder().customerName("Ram").password("#knagamma1").build();
			AccountData accountData1 = AccountData.builder().accountNumber(9945944331l).balance(500000d).build();
			AccountData accountData2 = AccountData.builder().accountNumber(9686597622l).balance(500000d).build();
			List<AccountData> accountData = new ArrayList<>();
			accountData.add(accountData1);
			accountData.add(accountData2);
			customerData.setAccountData(accountData);
			customerRepository.save(customerData);*/

			/*AccountData accountData = AccountData.builder().accountId(1l).accountNumber(9945944331l).balance(500000d).build();
			List<PortfolioData> portfolioDataList = new ArrayList<>();
			PortfolioData apple = PortfolioData.builder().stockName("Apple").stockQuantity(10).purchasePrice(500l).build();
			PortfolioData ibm = PortfolioData.builder().stockName("IBM").stockQuantity(20).purchasePrice(200l).build();
			PortfolioData tesla = PortfolioData.builder().stockName("Tesla").stockQuantity(30).purchasePrice(300l).build();
			PortfolioData ge = PortfolioData.builder().stockName("General Motors").stockQuantity(40).purchasePrice(200l).build();
			portfolioDataList.add(apple);
			portfolioDataList.add(ibm);
			portfolioDataList.add(tesla);
			portfolioDataList.add(ge);
			accountData.setPortfolioList(portfolioDataList);
			accountRepository.save(accountData);*/
		};
	}
}
