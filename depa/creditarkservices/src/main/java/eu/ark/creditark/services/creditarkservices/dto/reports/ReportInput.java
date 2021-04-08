package eu.ark.creditark.services.creditarkservices.dto.reports;

import eu.ark.creditark.services.creditarkservices.dto.ClienteleDistributionDto;
import eu.ark.creditark.services.creditarkservices.dto.ClienteleStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.ClienteleStatisticsGraphDto;
import eu.ark.creditark.services.creditarkservices.dto.CustomerDetailsDto;

public class ReportInput {
    private ClienteleDistributionDto clienteleDistributionDto;
    private CustomerDetailsDto customerDetailsDto;
    private ClienteleStatisticsDto clienteleStatisticsDto;
    private ClienteleStatisticsGraphDto clienteleStatisticsGraphDto;

    private ReportInput(ClienteleDistributionDto clienteleDistributionDto,
                       CustomerDetailsDto customerDetailsDto,
                       ClienteleStatisticsDto clienteleStatisticsDto,
                       ClienteleStatisticsGraphDto clienteleStatisticsGraphDto) {
        this.clienteleDistributionDto = clienteleDistributionDto;
        this.customerDetailsDto = customerDetailsDto;
        this.clienteleStatisticsDto = clienteleStatisticsDto;
        this.clienteleStatisticsGraphDto = clienteleStatisticsGraphDto;
    }

    public ClienteleDistributionDto getClienteleDistributionDto() {
        return clienteleDistributionDto;
    }

    public CustomerDetailsDto getCustomerDetailsDto() {
        return customerDetailsDto;
    }

    public ClienteleStatisticsDto getClienteleStatisticsDto() {
        return clienteleStatisticsDto;
    }

    public ClienteleStatisticsGraphDto getClienteleStatisticsGraphDto() {
        return clienteleStatisticsGraphDto;
    }

    public static class Builder {
        private ClienteleDistributionDto clienteleDistributionDto;
        private CustomerDetailsDto customerDetailsDto;
        private ClienteleStatisticsDto clienteleStatisticsDto;
        private ClienteleStatisticsGraphDto clienteleStatisticsGraphDto;


        public Builder withClienteleDistributionDto(ClienteleDistributionDto clienteleDistributionDto) {
            this.clienteleDistributionDto = clienteleDistributionDto;
            return this;
        }

        public Builder withCustomerDetailsDto(CustomerDetailsDto customerDetailsDto) {
            this.customerDetailsDto = customerDetailsDto;
            return this;
        }

        public Builder withClienteleStatisticsDto(ClienteleStatisticsDto clienteleStatisticsDto) {
            this.clienteleStatisticsDto = clienteleStatisticsDto;
            return this;
        }

        public Builder withClienteleStatisticsGraphDto(ClienteleStatisticsGraphDto clienteleStatisticsGraphDto) {
            this.clienteleStatisticsGraphDto = clienteleStatisticsGraphDto;
            return this;
        }

        public ReportInput build() {
            return  new ReportInput(this.clienteleDistributionDto,
                    this.customerDetailsDto,
                    this.clienteleStatisticsDto,
                    this.clienteleStatisticsGraphDto);
        }
    }
}
