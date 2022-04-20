def energy_to_time(energy_limit, current_limit=16, voltage_limit=220):
    """
    energy_limit unit: kWh
    current_limit: A
    voltage_limit: V
    return unit: min
    """
    assert type(energy_limit) == int # only allow for integer inputs

    current = current_limit
    voltage = voltage_limit
    power = current * voltage / 1000 # (kw) 3.52kW for our case
    time_est = round( (energy_limit / power) * 60 )

    return time_est # in minutes