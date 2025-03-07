package br.csi.interfaces;


public interface ClientRequestStatus {
    public String City = " and upper(a.city) = upper(:city)";
    public String State = " and upper(a.state) = upper(:state)";
    public String Street = " and upper(a.street) = upper(:street)";
}
