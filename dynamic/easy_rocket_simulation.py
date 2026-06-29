import numpy as np
import matplotlib.pyplot as plt


# Rocket parameters 
m0 = 1197           # initial total mass (kg)
mdry = 200          # mass without fuel (kg)
mdot = 40           # mass decrease rate (kg / s)
u = 2000            # exhaust velocity relative to nozzle (m / s)
Cd = 0.75           # drag coefficient
r = 0.22            # radius of the rocket
A = r * r * np.pi   # cross-sectional area 

# Atmosphere and gravity parameters
R = 287.05          # Gas constant for air(J / (kg * K) )
p0 = 101325         # Pressure at sea level(Pa)
p11 = 22631.7       # Pressure at altitude 11km(Pa)
p20 = 5474.9        # Pressure at altitude 20km(Pa)
p32 = 868.02        # Pressure at altitude 32km(Pa)
p47 = 110.91        # Pressure at altitude 47km(Pa)
p51 = 66.939        # Pressure at altitude 51km(Pa)
p71 = 3.9564        # Pressure at altitude 71km(Pa)
L0 = 0.0065          # lapse rate(temperature increases rate) at Troposphere(K / m)
L11 = 0             # lapse rate above altitude 11km (K / m)
L20 = -0.001        # lapse rate above altitude 20km (K / m)
L32 = -0.0028          # lapse rate above altitude 32km (K / m)
L47 = 0             # lapse rate above altitude 47km (K / m)
L51 = 0.0028           # lapse rate above altitude 51km (K / m)
L71 = 0.002           # lapse rate above altitude 71km (K / m)
T0 = 288.15         # temperature at sea level (K)
T11 = 216.65        # temperature at altitude 11km (K)
T20 = 216.65        # temperature at altitude 20km (K)
T32 = 228.65        # temperature at altitude 32km (K)
T47 = 270.65        # temperature at altitude 47km (K)
T51 = 270.65        # temperature at altitude 51km (K)
T71 = 214.65        # temperature at altitude 71km (K)
g0 = 9.80665        # gravitational acceleration at sea level (m / s^2)
Re = 6371124.526    # Earth's radius (m)


# Simulation parameters for RK4
dt = 0.01           # time step (s)
t_max = 100         # maximum simulation time (s)


# state and function
 

 # ISA model - Barometric formula
def pressure(h):
    if(h <= 11000):
        return p0 * (temperature(h) / T0 )**( g0 / ( R * L0 ))
    elif(h <= 20000):
        return p11*(np.exp(( ( -g0 ) * ( h - 11000 ) ) / ( T11 * R ) ) )
    elif(h <= 32000):
        return p20 * (temperature(h) / T20 )**( g0 / ( R * L20 ))
    elif(h <= 47000):
        return p32 * (temperature(h) / T32 )**( g0 / ( R * L32 ))
    elif(h <= 51000):
        return p47*(np.exp(( ( -g0 ) * ( h - 47000 ) ) / ( T47 * R ) ) )
    elif(h <= 71000):
        return p51 * (temperature(h) / T51 )**( g0 / ( R * L51 ))
    else:
        return p71 *(temperature(h) / T71 )**( g0 / ( R * L71 ))

# ISA model
def temperature(h):
    if(h <= 11000):
        return T0 - L0 * h
    elif(h <= 20000):
        return T11 - L11 * (h - 11000)
    elif(h <= 32000):
        return T20 - L20 * (h - 20000)
    elif(h <= 47000):
        return T32 - L32 * (h - 32000)
    elif(h <= 51000):
        return T47 - L47 * (h - 47000)
    elif(h <= 71000):
        return T51 - L51 * (h - 51000)
    else:
        return T71 - L71 * (h - 71000)

# ideal gas equation
def density(h):
    return pressure(h) / (R * temperature(h))   

def gravity(h):
    return g0 * ( Re / ( Re + h ))**2

def thrust(mdot, u):
    return mdot * u

def drag(v, h):
    return 0.5 * density(h) * Cd * A * v * abs(v)


# compute the derivatives for RK4 like dv/dt, dh/dt, dm/dt
# although t is not being used for now, I keep it as insurance if I need to apply in the future
def rocket_derivatives(t, state): #compute d.. / d..
    v, h, m = state
    if m <= mdry:        # when fuel runs out
        md = 0
        T = 0
    else:
        md = -mdot       # when fuel is still sufficient, and m' < 0
        T = thrust(mdot, u)

    D = drag(v, h)
    g = gravity(h)

    dvdt = (T - D) / m - g
    dhdt = v
    dmdt = md

    return np.array([dvdt, dhdt, dmdt])

#RK4 method, k is the slope of state row at point t = current t
def rk4_step(t, state, dt):
    k1 = rocket_derivatives(t, state)
    k2 = rocket_derivatives(t + dt / 2, state + dt * k1 / 2)
    k3 = rocket_derivatives(t + dt / 2, state + dt * k2 / 2)
    k4 = rocket_derivatives(t + dt, state + dt * k3)
    return state + dt * (k1 + 2 * k2 + 2 * k3 + k4) / 6 #average of the slope
    #return state_i+1 = state_i + k(slope) * delta_t 




# ---------------------------------------------------------------------------------------
# program entry, simulation part
# null array
times = []          #null array for time
vels = []           
alts = []
masses = []
pressures = []
temperatures = []


# initial condition --> t = 0, h = 0, v = 0, m = m0
t = 0                                
state = np.array([0.0, 0.0, m0])   # v, h, m


# iteration
while t < t_max and state[1] < 82000: # SAI model won't fully work above altitude 82km
    times.append(t)
    vels.append(state[0])
    alts.append(state[1])
    masses.append(state[2])

    pressures.append(pressure(state[1]))
    temperatures.append(temperature(state[1]))

    state = rk4_step(t, state, dt)
    t += dt


# Plot results
plt.figure(figsize=(9, 5))
plt.plot(alts, pressures)
plt.title("ISA pressure")
plt.xlabel("pressure ")
plt.ylabel("altitude")
plt.grid(True)

plt.figure(figsize=(9, 5))
plt.plot(alts, temperatures)
plt.title("ISA temperature")
plt.xlabel("altitude ")
plt.ylabel("temperature")
plt.grid(True)


plt.figure(figsize=(9, 5))
plt.plot(times, vels)
plt.title("Rocket Velocity vs Time")
plt.xlabel("Time (s)")
plt.ylabel("Velocity (m/s)")
plt.grid(True)

plt.figure(figsize=(9, 5))
plt.plot(times, alts)
plt.title("Rocket Altitude vs Time")
plt.xlabel("Time (s)")
plt.ylabel("Altitude (m)")
plt.grid(True)


plt.figure(figsize=(9, 5))
plt.plot(times, masses)
plt.title("Rocket masses vs Time")
plt.xlabel("Time (s)")
plt.ylabel("masses (kg)")
plt.grid(True)

plt.figure(figsize=(9, 5))
plt.plot(alts, vels)
plt.title("Rocket Vertical Velocity vs altitude")
plt.xlabel("altitude (m)")
plt.ylabel("Velocity (m/s)")
plt.grid(True)

plt.show()
