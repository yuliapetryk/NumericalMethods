import math
import numpy as np
import matplotlib.pyplot as plt

def function0(x):
    return 3 ** x

def function1(x):
    return 2 * x ** 2 - x + 1

def function2(x):
    return 2 * math.sin(3*x)

def function3(x):
    return x ** 2 - 3 * math.sin(3 * x)

def polynomial_lagrange(points, func_value):
    result = np.poly1d([0])
    n = len(points)

    for i in range(n):
        l = np.poly1d([func_value[i]])
        for j in range(n):
            if j != i:
                l *= np.poly1d([1, - points[j]]) / (points[i] - points[j])
        result += l
    return result

def polynomial_newton(points, func_value):
    differences = []
    for i in range(0, len(points)):
        if i == 0:
            differences.append(func_value)
            continue
        differences.append(calculate_differences(i, points, differences[i - 1]))

    result = np.poly1d([differences[0][0]])
    for i in range(1, len(differences)):
        temp = np.poly1d([differences[i][0]])
        for j in range(0, i):
           temp *= np.poly1d([1, -points[j]])
        result += temp
    return result

def calculate_differences(index, points,  differences):
    result = np.zeros(len(points) - index, dtype=float)
    i, j = 0, 0
    while (i + index) < len(points):
        result[j] = ( differences[i + 1] - differences[i]) / (points[i + index] - points[i])
        j += 1
        i += 1
    return result

def cubic_spline_coef(points, func_value):
    n = len(points)
    h = np.diff(points)

    func_value_diff = np.diff(func_value)
    func_value_diff /= h

    w = np.empty(n-1, float)
    z = np.empty(n, float)

    w[0] = 0.
    z[0] = 0.
    for i in range(1, n - 1):
        m = h[i-1] * (2 - w[i-1]) + 2 * h[i]
        w[i] = h[i] / m
        z[i] = (6*(func_value_diff[i] - func_value_diff[i-1]) - h[i-1]*z[i-1]) / m
    z[-1] = 0.

    for i in range(n-2, -1, -1):
        z[i] = z[i] - w[i]*z[i+1]

    return z

def evaluate_cubic_spline(points, xi, func_value):
    z = cubic_spline_coef(points, func_value)
    index = points.searchsorted(xi)
    np.clip(index, 1, len(points)-1, index)

    points1, points0 = points[index], points[index-1]
    func_value1, func_value0 = func_value[index], func_value[index-1]
    z1, z0 = z[index], z[index-1]

    h = points1 - points0

    result = z0 / (6 * h) * (points1 - xi) ** 3 + z1 / (6 * h) * (xi - points0) ** 3 + (func_value1 / h - z1 * h/6) * (xi - points0) + (func_value0 / h - z0 * h / 6) * (points1 - xi)
    return result

if __name__ == "__main__":
    points =np.linspace(-1, 3, 50)

    func_value = []
    for x in points:
        func_value.append(function3(x))

    lagrange = polynomial_lagrange(points, func_value)
    newton = polynomial_newton(points, func_value)
    print(lagrange)
    print(newton)
    points = np.asfarray(points)
    func_value = np.asfarray(func_value)
    plt.figure(figsize=(20, 8))
    value = np.linspace(points.min(), points.max(), 1000)
    plt.subplot(1, 3, 1)
    plt.plot(value, [np.polyval(lagrange, x) for x in value], linestyle='-', label='Інтерполяційний поліном Лагранжа')
    plt.legend()

    plt.subplot(1, 3, 2)
    plt.plot(value, [np.polyval(newton, x) for x in value], linestyle='-', label='Інтерполяційний поліном Ньютона')
    plt.legend()



    x_vals = np.linspace(points.min(), points.max(), 1000)
    y_vals = evaluate_cubic_spline(points, x_vals, func_value)

    plt.subplot(1, 3, 3)

    plt.plot(x_vals, y_vals, label='Інтерполяційний кубічний сплайн')
    plt.legend()
    plt.show()




